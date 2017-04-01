/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-30
 */
package com.nswt.osp.client;

import com.nswt.osp.CommonConstant;
import com.nswt.osp.MessageFormat;
import com.nswt.osp.OspRequest;
import com.nswt.osp.annotation.Temporary;
import com.nswt.osp.client.unmarshaller.JaxbXmlOspUnmarshaller;
import com.nswt.osp.marshaller.MessageMarshallerUtils;
import com.nswt.osp.request.OspConverter;
import com.nswt.osp.response.ErrorResponse;
import com.nswt.osp.utils.OspUtils;
import com.nswt.osp.annotation.IgnoreSign;
import com.nswt.osp.client.unmarshaller.JacksonJsonOspUnmarshaller;
import com.nswt.osp.config.SystemParameterNames;
import com.nswt.osp.impl.DefaultOspContext;
import com.nswt.osp.request.UploadFile;
import com.nswt.osp.request.UploadFileConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.*;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class DefaultOspClient implements OspClient {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    //服务地址
    private String serverUrl;

    //应用键
    private String appKey;

    //应用密钥
    private String appSecret;

    private String sessionId;

    //报文格式
    private MessageFormat messageFormat = MessageFormat.xml;

    private Locale locale = Locale.SIMPLIFIED_CHINESE;

    private RestTemplate restTemplate = new RestTemplate();

    private OspUnmarshaller xmlUnmarshaller = new JaxbXmlOspUnmarshaller();

    private OspUnmarshaller jsonUnmarshaller = new JacksonJsonOspUnmarshaller();

    //请求类所有请求参数
    private Map<Class<?>, List<Field>> requestAllFields = new HashMap<Class<?>, List<Field>>();

    //请求类所有不需要进行签名的参数
    private Map<Class<?>, List<String>> requestIgnoreSignFieldNames = new HashMap<Class<?>, List<String>>();


    //键为转换的目标类型
    private static Map<Class<?>, OspConverter<String, ?>> ospConverterMap =
            new HashMap<Class<?>, OspConverter<String, ?>>();
    {
        ospConverterMap.put(UploadFile.class, new UploadFileConverter());
    }

    public DefaultOspClient(String serverUrl, String appKey, String appSecret) {
        this.serverUrl = serverUrl;
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    public DefaultOspClient(String serverUrl, String appKey, String appSecret, MessageFormat messageFormat) {
        this.serverUrl = serverUrl;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.messageFormat = messageFormat;
    }

    public DefaultOspClient(String serverUrl, String appKey, String appSecret, MessageFormat messageFormat, Locale locale) {
        this.serverUrl = serverUrl;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.messageFormat = messageFormat;
        this.locale = locale;
    }


    public MessageFormat getMessageFormat() {
        return messageFormat;
    }

    public void setMessageFormat(MessageFormat messageFormat) {
        this.messageFormat = messageFormat;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }


    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


    public OspClient setAppKeyParamName(String paramName) {
        SystemParameterNames.setAppKey(paramName);
        return this;
    }


    public OspClient setSessionIdParamName(String paramName) {
        SystemParameterNames.setSessionId(paramName);
        return this;
    }


    public OspClient setMethodParamName(String paramName) {
        SystemParameterNames.setMethod(paramName);
        return this;
    }


    public OspClient setVersionParamName(String paramName) {
        SystemParameterNames.setVersion(paramName);
        return this;
    }


    public OspClient setFormatParamName(String paramName) {
        SystemParameterNames.setFormat(paramName);
        return this;
    }


    public OspClient setLocaleParamName(String paramName) {
        SystemParameterNames.setLocale(paramName);
        return this;
    }


    public OspClient setSignParamName(String paramName) {
        SystemParameterNames.setSign(paramName);
        return this;
    }


    public void addOspConvertor(OspConverter ospConverter) {
        this.ospConverterMap.put(ospConverter.getTargetClass(), ospConverter);
    }


    public ClientRequest buildClientRequest() {
        return new DefaultClientRequest(this);
    }

    private class DefaultClientRequest implements ClientRequest {

        private OspClient ospClient;

        private Map<String, String> paramMap = new HashMap<String, String>(20);

        private List<String> ignoreSignParams = new ArrayList<String>();

        private DefaultClientRequest(OspClient ospClient) {
            this.ospClient = ospClient;
            paramMap.put(SystemParameterNames.getAppKey(), appKey);
            paramMap.put(SystemParameterNames.getFormat(), messageFormat.name());
            paramMap.put(SystemParameterNames.getLocale(), locale.toString());
            if (sessionId != null) {
                paramMap.put(SystemParameterNames.getSessionId(), sessionId);
            }
        }


        public ClientRequest addParam(String paramName, Object paramValue) {
            addParam(paramName,paramValue,false);
            return this;
        }


        public ClientRequest clearParam() {
            paramMap.clear();
            return this;
        }


        public ClientRequest addParam(String paramName, Object paramValue, boolean ignoreSign) {
            Assert.isTrue(paramName != null && paramName.length() > 0, "参数名不能为空");
            Assert.notNull(paramValue, "参数值不能为null");

            //将参数添加到参数列表中
            String valueAsStr = paramValue.toString();
            if (ospConverterMap.containsKey(paramValue.getClass())) {
                OspConverter ospConverter = ospConverterMap.get(paramValue.getClass());
                valueAsStr = (String) ospConverter.unconvert(paramValue);
            }
            paramMap.put(paramName, valueAsStr);

            IgnoreSign typeIgnore = AnnotationUtils.findAnnotation(paramValue.getClass(), IgnoreSign.class);
            if (ignoreSign || typeIgnore != null) {
                ignoreSignParams.add(paramName);
            }
            return this;
        }


        public <T> CompositeResponse post(Class<T> ospResponseClass, String methodName, String version) {
            Map<String, String> requestParams = addOtherParamMap(methodName, version);
            return post(ospResponseClass, requestParams);
        }


        public <T> CompositeResponse post(OspRequest ospRequest, Class<T> ospResponseClass, String methodName, String version) {
            Map<String, String> requestParams = getRequestForm(ospRequest, methodName, version);
            return post(ospResponseClass, requestParams);
        }

        private <T> CompositeResponse post(Class<T> ospResponseClass, Map<String, String> requestParams) {
            String responseContent = restTemplate.postForObject(serverUrl, toMultiValueMap(requestParams), String.class);
            if (logger.isDebugEnabled()) {
                logger.debug("response:\n" + responseContent);
            }
            return toCompositeResponse(responseContent, ospResponseClass);
        }


        public <T> CompositeResponse get(Class<T> ospResponseClass, String methodName, String version) {
            Map<String, String> requestParams = addOtherParamMap(methodName, version);
            return get(ospResponseClass, requestParams);
        }


        public <T> CompositeResponse get(OspRequest ospRequest, Class<T> ospResponseClass, String methodName, String version) {
            Map<String, String> requestParams = getRequestForm(ospRequest, methodName, version);
            return get(ospResponseClass, requestParams);
        }

        private <T> CompositeResponse get(Class<T> ospResponseClass, Map<String, String> requestParams) {
            String responseContent = restTemplate.getForObject(buildGetUrl(requestParams), String.class, requestParams);
            if (logger.isDebugEnabled()) {
                logger.debug("response:\n" + responseContent);
            }
            return toCompositeResponse(responseContent, ospResponseClass);
        }

        private Map<String, String> addOtherParamMap(String methodName, String version) {
            paramMap.put(SystemParameterNames.getMethod(), methodName);
            paramMap.put(SystemParameterNames.getVersion(), version);
            String signValue = OspUtils.sign(paramMap, ignoreSignParams, appSecret);
            paramMap.put(SystemParameterNames.getSign(), signValue);
            return paramMap;
        }

        private <T> CompositeResponse toCompositeResponse(String content, Class<T> ospResponseClass) {
            if(logger.isDebugEnabled()){
                logger.debug(content);
            }
            boolean successful = isSuccessful(content);
            DefaultCompositeResponse<T> compositeResponse = new DefaultCompositeResponse<T>(successful);

            if (MessageFormat.json == messageFormat) {
                if (successful) {
                    T ospResponse = jsonUnmarshaller.unmarshaller(content, ospResponseClass);
                    compositeResponse.setSuccessRopResponse(ospResponse);
                } else {
                    ErrorResponse errorResponse = jsonUnmarshaller.unmarshaller(content, ErrorResponse.class);
                    compositeResponse.setErrorResponse(errorResponse);
                }
            } else {
                if (successful) {
                    T ospResponse = xmlUnmarshaller.unmarshaller(content, ospResponseClass);
                    compositeResponse.setSuccessRopResponse(ospResponse);
                } else {
                    ErrorResponse errorResponse = xmlUnmarshaller.unmarshaller(content, ErrorResponse.class);
                    compositeResponse.setErrorResponse(errorResponse);
                }
            }
            return compositeResponse;
        }

        private boolean isSuccessful(String content) {
            return !(content.contains(CommonConstant.ERROR_TOKEN));
        }

        private String buildGetUrl(Map<String, String> form) {
            StringBuilder requestUrl = new StringBuilder();
            requestUrl.append(serverUrl);
            requestUrl.append("?");
            String joinChar = "";
            for (Map.Entry<String, String> entry : form.entrySet()) {
                requestUrl.append(joinChar);
                requestUrl.append(entry.getKey());
                requestUrl.append("=");
                requestUrl.append(entry.getValue());
                joinChar = "&";
            }
            return requestUrl.toString();
        }

        private Map<String, String> getRequestForm(OspRequest ospRequest, String methodName, String version) {

            Map<String, String> form = new LinkedHashMap<String, String>(16);

            //系统级参数
            form.put(SystemParameterNames.getAppKey(), appKey);
            form.put(SystemParameterNames.getMethod(), methodName);
            form.put(SystemParameterNames.getVersion(), version);
            form.put(SystemParameterNames.getFormat(), messageFormat.name());
            form.put(SystemParameterNames.getLocale(), locale.toString());
            if (sessionId != null) {
                form.put(SystemParameterNames.getSessionId(), sessionId);
            }

            //业务级参数
            form.putAll(getParamFields(ospRequest, messageFormat));

            //对请求进行签名
            String signValue = sign(ospRequest.getClass(), appSecret, form);
            form.put("sign", signValue);
            return form;
        }

        private MultiValueMap<String, String> toMultiValueMap(Map<String, String> form) {
            MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
            for (Map.Entry<String, String> entry : form.entrySet()) {
                mvm.add(entry.getKey(), entry.getValue());
            }
            return mvm;
        }

        /**
         * 对请求参数进行签名
         *
         * @param ospRequestClass
         * @param appSecret
         * @param form
         * @return
         */
        private String sign(Class<?> ospRequestClass, String appSecret, Map<String, String> form) {
            List<String> ignoreFieldNames = requestIgnoreSignFieldNames.get(ospRequestClass);
            return OspUtils.sign(form, ignoreFieldNames, appSecret);
        }

        /**
         * 获取ospRequest对应的参数名列表
         *
         * @param ospRequest
         * @param mf
         * @return
         */
        private Map<String, String> getParamFields(OspRequest ospRequest, MessageFormat mf) {
            if (!requestAllFields.containsKey(ospRequest.getClass())) {
                parseOspRequestClass(ospRequest);
            }
            return toParamValueMap(ospRequest, mf);
        }

        /**
         * 获取Request对象的对应的参数列表
         *
         * @param ospRequest
         * @param mf
         * @return
         */
        private Map<String, String> toParamValueMap(OspRequest ospRequest, MessageFormat mf) {
            List<Field> fields = requestAllFields.get(ospRequest.getClass());
            Map<String, String> params = new HashMap<String, String>();
            for (Field field : fields) {
                OspConverter convertor = getConvertor(field.getType());
                Object fieldValue = ReflectionUtils.getField(field, ospRequest);
                if (fieldValue != null) {
                    if (convertor != null) {//有对应转换器
                        String strParamValue = (String) convertor.unconvert(fieldValue);
                        params.put(field.getName(), strParamValue);
                    } else if (field.getType().isAnnotationPresent(XmlRootElement.class) ||
                            field.getType().isAnnotationPresent(XmlType.class)) {
                        String message = MessageMarshallerUtils.getMessage(fieldValue, mf);
                        params.put(field.getName(), message);
                    } else {
                        params.put(field.getName(), fieldValue.toString());
                    }
                }
            }
            return params;
        }
    }

    private OspConverter getConvertor(Class<?> fieldType) {
        for (Class<?> aClass : ospConverterMap.keySet()) {
            if (ClassUtils.isAssignable(aClass, fieldType)) {
                return ospConverterMap.get(aClass);
            }
        }
        return null;
    }

    private void parseOspRequestClass(OspRequest ospRequest) {
        final ArrayList<Field> allFields = new ArrayList<Field>();
        final List<String> ignoreSignFieldNames = DefaultOspContext.getIgnoreSignFieldNames(ospRequest.getClass());
        ReflectionUtils.doWithFields(ospRequest.getClass(), new ReflectionUtils.FieldCallback() {

            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                ReflectionUtils.makeAccessible(field);
                if (!isTemporaryField(field)) {
                    allFields.add(field);
                }
            }

            private boolean isTemporaryField(Field field) {
                Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
                if (declaredAnnotations != null) {
                    for (Annotation declaredAnnotation : declaredAnnotations) {
                        Temporary varTemporary = field.getAnnotation(Temporary.class);
                        if (varTemporary != null) {
                            return true;
                        }
                    }
                }
                return false;
            }
        });

        requestAllFields.put(ospRequest.getClass(), allFields);
        requestIgnoreSignFieldNames.put(ospRequest.getClass(), ignoreSignFieldNames);
    }

    /**
     * 获取ospRequest对应的参数名列表
     *
     * @param ospRequest
     * @param mf
     * @return
     */
    private Map<String, String> getParamFields(OspRequest ospRequest, MessageFormat mf) {
        if (!requestAllFields.containsKey(ospRequest.getClass())) {
            parseOspRequestClass(ospRequest);
        }
        return toParamValueMap(ospRequest, mf);
    }

    /**
     * 获取ospRequest对象的对应的参数列表
     *
     * @param ospRequest
     * @param mf
     * @return
     */
    private Map<String, String> toParamValueMap(OspRequest ospRequest, MessageFormat mf) {
        List<Field> fields = requestAllFields.get(ospRequest.getClass());
        Map<String, String> params = new HashMap<String, String>();
        for (Field field : fields) {
            OspConverter convertor = getConvertor(field.getType());
            Object fieldValue = ReflectionUtils.getField(field, ospRequest);
            if (fieldValue != null) {
                if (convertor != null) {//有对应转换器
                    String strParamValue = (String) convertor.unconvert(fieldValue);
                    params.put(field.getName(), strParamValue);
                } else if (field.getType().isAnnotationPresent(XmlRootElement.class) ||
                        field.getType().isAnnotationPresent(XmlType.class)) {
                    String message = MessageMarshallerUtils.getMessage(fieldValue, mf);
                    params.put(field.getName(), message);
                } else {
                    params.put(field.getName(), fieldValue.toString());
                }
            }
        }
        return params;
    }


}

