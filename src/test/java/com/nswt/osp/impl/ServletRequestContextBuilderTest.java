/**
 * 版权声明：开源随便使用
 * 日    期：16-6-12
 */
package com.nswt.osp.impl;

import com.nswt.osp.MessageFormat;
import com.nswt.osp.session.SessionManager;
import com.nswt.osp.OspContext;
import com.nswt.osp.ServiceMethodHandler;
import com.nswt.osp.config.SystemParameterNames;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mock.web.MockHttpServletRequest;
import org.testng.annotations.Test;

import java.util.Locale;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class ServletRequestContextBuilderTest {

    @Test
    public void testIpParsed() {
        FormattingConversionService conversionService = mock(FormattingConversionService.class);
        SessionManager sessionManager = mock(SessionManager.class);
        ServletRequestContextBuilder requestContextBuilder = new ServletRequestContextBuilder(conversionService);
        OspContext ospContext = mock(OspContext.class);

        //构造HttpServletRequest
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        servletRequest.setRemoteAddr("1.1.1.1");

        //创建SimpleRequestContext
        SimpleOspRequestContext requestContext = requestContextBuilder.buildBySysParams(ospContext, servletRequest,null);
        assertEquals(requestContext.getIp(), "1.1.1.1");

        servletRequest.setRemoteAddr("1.1.1.1");
        servletRequest.addHeader(ServletRequestContextBuilder.X_FORWARDED_FOR, "null,2.2.2.2,3.3.3.3");
        requestContext = requestContextBuilder.buildBySysParams(ospContext, servletRequest,null);
        assertEquals(requestContext.getIp(), "2.2.2.2");

        servletRequest.addHeader(ServletRequestContextBuilder.X_REAL_IP, "5.5.5.5");
        requestContext = requestContextBuilder.buildBySysParams(ospContext, servletRequest,null);
        assertEquals(requestContext.getIp(), "5.5.5.5");

    }

    /**
     * 正常情况下的系统参数绑定
     *
     * @throws Exception
     */
    @Test
    public void testBuildBySysParams1() throws Exception {
        FormattingConversionService conversionService = mock(FormattingConversionService.class);
        SessionManager sessionManager = mock(SessionManager.class);
        ServletRequestContextBuilder requestContextBuilder = new ServletRequestContextBuilder(conversionService);

        OspContext ospContext = mock(OspContext.class);
        ServiceMethodHandler methodHandler = mock(ServiceMethodHandler.class);
        when(ospContext.getServiceMethodHandler("method1", "3.0")).thenReturn(methodHandler);

        //构造HttpServletRequest
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();

        servletRequest.setParameter(SystemParameterNames.getAppKey(), "appKey1");
        servletRequest.setParameter(SystemParameterNames.getSessionId(), "sessionId1");
        servletRequest.setParameter(SystemParameterNames.getMethod(), "method1");
        servletRequest.setParameter(SystemParameterNames.getVersion(), "3.0");
        servletRequest.setParameter(SystemParameterNames.getLocale(), "en_UK");
        servletRequest.setParameter(SystemParameterNames.getFormat(), "xml");
        servletRequest.setParameter(SystemParameterNames.getSign(), "sign1");
        servletRequest.setParameter("param1", "value1");
        servletRequest.setParameter("param2", "value2");
        servletRequest.setParameter("param3", "value3");

        //创建SimpleRequestContext
        SimpleOspRequestContext requestContext =
                requestContextBuilder.buildBySysParams(ospContext, servletRequest,null);

        assertEquals(requestContext.getAllParams().size(), 10);
        assertEquals(requestContext.getParamValue("param1"), "value1");
        assertEquals(requestContext.getRawRequestObject(), servletRequest);

        assertEquals(requestContext.getAppKey(), "appKey1");
        assertEquals(requestContext.getSessionId(), "sessionId1");
        assertEquals(requestContext.getMethod(), "method1");
        assertEquals(requestContext.getVersion(), "3.0");
        assertEquals(requestContext.getLocale(), new Locale("zh", "CN"));
        assertEquals(requestContext.getFormat(), "xml");
        assertEquals(requestContext.getMessageFormat(), MessageFormat.xml);
        assertEquals(requestContext.getSign(), "sign1");

        assertEquals(requestContext.getServiceMethodHandler(), methodHandler);
    }

    /**
     * 看错误的参数是否会被自动转为默认的
     *
     * @throws Exception
     */
    @Test
    public void testBuildBySysParams2() throws Exception {
        FormattingConversionService conversionService = mock(FormattingConversionService.class);
        SessionManager sessionManager = mock(SessionManager.class);
        ServletRequestContextBuilder requestContextBuilder = new ServletRequestContextBuilder(conversionService);
        OspContext ospContext = mock(OspContext.class);


        //构造HttpServletRequest
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        servletRequest.setParameter(SystemParameterNames.getLocale(), "xxx");
        servletRequest.setParameter(SystemParameterNames.getFormat(), "xxx");


        //创建SimpleRequestContext
        SimpleOspRequestContext requestContext =
                requestContextBuilder.buildBySysParams(ospContext, servletRequest,null);
        assertEquals(requestContext.getLocale(), Locale.SIMPLIFIED_CHINESE);
        assertEquals(requestContext.getFormat(), "xxx");
        assertEquals(requestContext.getMessageFormat(), MessageFormat.xml);

    }

    /**
     * 非{@link javax.servlet.http.HttpServletRequest}
     *
     * @throws Exception
     */
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testBuildBySysParams3() throws Exception {
        FormattingConversionService conversionService = mock(FormattingConversionService.class);
        ServletRequestContextBuilder requestContextBuilder = new ServletRequestContextBuilder(conversionService);

        OspContext ospContext = mock(OspContext.class);
        ServiceMethodHandler methodHandler = mock(ServiceMethodHandler.class);
        when(ospContext.getServiceMethodHandler("method1", "3.0")).thenReturn(methodHandler);

        //创建SimpleRequestContext
        SimpleOspRequestContext requestContext =
                requestContextBuilder.buildBySysParams(ospContext, new Object(),null);
    }

    @Test
    public void testBindBusinessParams() throws Exception {

    }
}

