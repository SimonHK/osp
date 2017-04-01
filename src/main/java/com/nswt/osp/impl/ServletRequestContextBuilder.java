/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-1
 */
package com.nswt.osp.impl;

import com.nswt.osp.*;
import com.nswt.osp.annotation.HttpAction;
import com.nswt.osp.config.SystemParameterNames;
import com.nswt.osp.security.MainErrors;
import com.nswt.osp.security.MainErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * <pre>
 *    构建{@link OspRequestContext}实例
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class ServletRequestContextBuilder implements RequestContextBuilder {

    //通过前端的负载均衡服务器时，请求对象中的IP会变成负载均衡服务器的IP，因此需要特殊处理，下同。
    public static final String X_REAL_IP = "X-Real-IP";

    public static final String X_FORWARDED_FOR = "X-Forwarded-For";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private FormattingConversionService conversionService;

    private Validator validator;

    public ServletRequestContextBuilder(FormattingConversionService conversionService) {
        this.conversionService = conversionService;
    }


    public SimpleOspRequestContext buildBySysParams(OspContext ospContext,
                                                    Object request,
                                                    Object response) {
        if (!(request instanceof HttpServletRequest)) {
            throw new IllegalArgumentException("请求对象必须是HttpServletRequest的类型");
        }
        if(response != null && !(response instanceof HttpServletResponse)){
            throw new IllegalArgumentException("请求对象必须是HttpServletResponse的类型");
        }

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        SimpleOspRequestContext requestContext = new SimpleOspRequestContext(ospContext);

        //设置请求对象及参数列表
        requestContext.setRawRequestObject(servletRequest);
        if (response != null) {
            requestContext.setRawResponseObject(response);
        }
        requestContext.setAllParams(getRequestParams(servletRequest));
        requestContext.setIp(getRemoteAddr(servletRequest)); //感谢melin所指出的BUG

        //设置服务的系统级参数
        requestContext.setAppKey(servletRequest.getParameter(SystemParameterNames.getAppKey()));
        requestContext.setSessionId(servletRequest.getParameter(SystemParameterNames.getSessionId()));
        requestContext.setMethod(servletRequest.getParameter(SystemParameterNames.getMethod()));
        requestContext.setVersion(servletRequest.getParameter(SystemParameterNames.getVersion()));
        requestContext.setLocale(getLocale(servletRequest));
        requestContext.setFormat(getFormat(servletRequest));
        requestContext.setMessageFormat(getResponseFormat(servletRequest));
        requestContext.setSign(servletRequest.getParameter(SystemParameterNames.getSign()));
        requestContext.setHttpAction(HttpAction.fromValue(servletRequest.getMethod()));

        //设置服务处理器
        ServiceMethodHandler serviceMethodHandler =
                ospContext.getServiceMethodHandler(requestContext.getMethod(), requestContext.getVersion());
        requestContext.setServiceMethodHandler(serviceMethodHandler);

        return requestContext;
    }

    private String getRemoteAddr(HttpServletRequest request) {
        String remoteIp = request.getHeader(X_REAL_IP); //nginx反向代理
        if (StringUtils.hasText(remoteIp)) {
            return remoteIp;
        } else {
            remoteIp = request.getHeader(X_FORWARDED_FOR);//apache反射代理
            if (StringUtils.hasText(remoteIp)) {
                String[] ips = remoteIp.split(",");
                for (String ip : ips) {
                    if (!"null".equalsIgnoreCase(ip)) {
                        return ip;
                    }
                }
            }
            return request.getRemoteAddr();
        }
    }

    /**
     * 将{@link HttpServletRequest}的数据绑定到{@link OspRequestContext}的{@link OspRequest}中，同时使用
     * JSR 303对请求数据进行校验，将错误信息设置到{@link OspRequestContext}的属性列表中。
     *
     * @param ospRequestContext
     */

    public OspRequest buildOspRequest(OspRequestContext ospRequestContext) {
        AbstractOspRequest ospRequest = null;
        if (ospRequestContext.getServiceMethodHandler().isOspRequestImplType()) {
            HttpServletRequest request =
                    (HttpServletRequest) ospRequestContext.getRawRequestObject();
            BindingResult bindingResult = doBind(request, ospRequestContext.getServiceMethodHandler().getRequestType());
            ospRequest = buildOspRequestFromBindingResult(ospRequestContext, bindingResult);

            List<ObjectError> allErrors = bindingResult.getAllErrors();
            ospRequestContext.setAttribute(SimpleOspRequestContext.SPRING_VALIDATE_ERROR_ATTRNAME, allErrors);
        } else {
            ospRequest = new DefaultOspRequest();
        }
        ospRequest.setOspRequestContext(ospRequestContext);
        return ospRequest;
    }


    private String getFormat(HttpServletRequest servletRequest) {
        String messageFormat = servletRequest.getParameter(SystemParameterNames.getFormat());
        if (messageFormat == null) {
            return MessageFormat.xml.name();
        } else {
            return messageFormat;
        }
    }

    public static Locale getLocale(HttpServletRequest webRequest) {
        if (webRequest.getParameter(SystemParameterNames.getLocale()) != null) {
            try {
                LocaleEditor localeEditor = new LocaleEditor();
                localeEditor.setAsText(webRequest.getParameter(SystemParameterNames.getLocale()));
                Locale locale = (Locale) localeEditor.getValue();
                if (isValidLocale(locale)) {
                    return locale;
                }
            } catch (Exception e) {
                return Locale.SIMPLIFIED_CHINESE;
            }
        }
        return Locale.SIMPLIFIED_CHINESE;
    }

    private static boolean isValidLocale(Locale locale) {
        if (Locale.SIMPLIFIED_CHINESE.equals(locale) || Locale.ENGLISH.equals(locale)) {
            return true;
        } else {
            try {
                //check error resource file exists
                MainErrors.getError(MainErrorType.INVALID_APP_KEY, locale);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
    }


    public static MessageFormat getResponseFormat(HttpServletRequest servletRequest) {
        String messageFormat = servletRequest.getParameter(SystemParameterNames.getFormat());
        if (MessageFormat.isValidFormat(messageFormat)) {
            return MessageFormat.getFormat(messageFormat);
        } else {
            return MessageFormat.xml;
        }
    }

    private AbstractOspRequest buildOspRequestFromBindingResult(OspRequestContext ospRequestContext, BindingResult bindingResult) {
        AbstractOspRequest ospRequest = (AbstractOspRequest) bindingResult.getTarget();
        if (ospRequest instanceof AbstractOspRequest) {
            AbstractOspRequest abstractOspRequest = ospRequest;
            abstractOspRequest.setOspRequestContext(ospRequestContext);
        } else {
            logger.warn(ospRequest.getClass().getName() + "不是扩展于" + AbstractOspRequest.class.getName() +
                    ",无法设置" + OspRequestContext.class.getName());
        }
        return ospRequest;
    }

    private HashMap<String, String> getRequestParams(HttpServletRequest request) {
        Map srcParamMap = request.getParameterMap();
        HashMap<String, String> destParamMap = new HashMap<String, String>(srcParamMap.size());
        for (Object obj : srcParamMap.keySet()) {
            String[] values = (String[]) srcParamMap.get(obj);
            if (values != null && values.length > 0) {
                destParamMap.put((String) obj, values[0]);
            } else {
                destParamMap.put((String) obj, null);
            }
        }
        return destParamMap;
    }


    private BindingResult doBind(HttpServletRequest webRequest, Class<? extends OspRequest> requestType) {
        OspRequest bindObject = BeanUtils.instantiateClass(requestType);
        ServletRequestDataBinder dataBinder = new ServletRequestDataBinder(bindObject, "bindObject");
        dataBinder.setConversionService(getFormattingConversionService());
        dataBinder.setValidator(getValidator());
        dataBinder.bind(webRequest);
        dataBinder.validate();
        return dataBinder.getBindingResult();
    }

    private Validator getValidator() {
        if (this.validator == null) {
            LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
            localValidatorFactoryBean.afterPropertiesSet();
            this.validator = localValidatorFactoryBean;
        }
        return this.validator;
    }

    public FormattingConversionService getFormattingConversionService() {
        return conversionService;
    }

    //默认的{@link OspRequest}实现类
    private class DefaultOspRequest extends AbstractOspRequest {
    }
}

