/**
 *
 * 日    期：16-2-27
 */
package com.nswt.osp.impl;

import com.nswt.osp.*;
import com.nswt.osp.annotation.HttpAction;
import com.nswt.osp.security.MainError;
import com.nswt.osp.session.Session;
import com.nswt.osp.utils.OspUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Admin
 * @version 1.0
 */
public class SimpleOspRequestContext implements OspRequestContext {

    public static final String SPRING_VALIDATE_ERROR_ATTRNAME = "$SPRING_VALIDATE_ERROR_ATTRNAME";

    private OspContext ospContext;

    private String method;

    private String version;

    private String appKey;

    private String sessionId;

    private Locale locale;

    private String format;

    public static ThreadLocal<MessageFormat> messageFormat = new ThreadLocal<MessageFormat>();

    private String sign;

    private Map<String, Object> attributes = new HashMap<String, Object>();

    private ServiceMethodHandler serviceMethodHandler;

    private MainError mainError;

    private Object ospResponse;

    private OspRequest ospRequest;

    private long serviceBeginTime = -1;

    private long serviceEndTime = -1;

    private String ip;

    private HttpAction httpAction;

    private Object rawRequestObject;

    private Object rawResponseObject;

    private Map<String, String> allParams;

    private String requestId = OspUtils.getUUID();

    private Session session;


    public long getServiceBeginTime() {
        return this.serviceBeginTime;
    }


    public long getServiceEndTime() {
        return this.serviceEndTime;
    }


    public void setServiceBeginTime(long serviceBeginTime) {
        this.serviceBeginTime = serviceBeginTime;
    }


    public void setServiceEndTime(long serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }


    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }


    public Object getRawRequestObject() {
        return this.rawRequestObject;
    }


    public Object getRawResponseObject() {
        return this.rawResponseObject;
    }

    public void setRawRequestObject(Object rawRequestObject) {
        this.rawRequestObject = rawRequestObject;
    }

    public void setRawResponseObject(Object rawResponseObject) {
        this.rawResponseObject = rawResponseObject;
    }

    public SimpleOspRequestContext(OspContext ospContext) {
        this.ospContext = ospContext;
        this.serviceBeginTime = System.currentTimeMillis();
    }



    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    public OspContext getOspContext() {
        return ospContext;
    }


    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    public String getSessionId() {
        return this.sessionId;
    }


    public Session getSession() {
        if (session == null && ospContext != null &&
                ospContext.getSessionManager() != null && getSessionId() != null) {
           session = ospContext.getSessionManager().getSession(getSessionId());
        }
        return session;
    }


    public void addSession(String sessionId, Session session) {
        this.sessionId = sessionId;
        this.session = session;
        if (ospContext != null && ospContext.getSessionManager() != null) {
            ospContext.getSessionManager().addSession(sessionId, session);
        }
    }


    public void removeSession() {
        if (ospContext != null && ospContext.getSessionManager() != null) {
            ospContext.getSessionManager().removeSession(getSessionId());
        }
    }


    public Locale getLocale() {
        return this.locale;
    }

    public ServiceMethodHandler getServiceMethodHandler() {
        return this.serviceMethodHandler;
    }


    public MessageFormat getMessageFormat() {
        return messageFormat.get();
    }


    public Object getOspResponse() {
        return this.ospResponse;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setServiceMethodHandler(ServiceMethodHandler serviceMethodHandler) {
        this.serviceMethodHandler = serviceMethodHandler;
    }

    public void setMessageFormat(MessageFormat messageFormat) {
        this.messageFormat.set(messageFormat);
    }


    public void setOspResponse(Object ospResponse) {
        this.ospResponse = ospResponse;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setMainError(MainError mainError) {
        this.mainError = mainError;
    }

    public MainError getMainError() {
        return this.mainError;
    }


    public Object getAttribute(String name) {
        return this.attributes.get(name);
    }


    public void setAttribute(String name, Object value) {
        this.attributes.put(name, value);
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }


    public boolean isSignEnable() {
        return ospContext.isSignEnable() && !getServiceMethodDefinition().isIgnoreSign();
    }


    public ServiceMethodDefinition getServiceMethodDefinition() {
        return serviceMethodHandler.getServiceMethodDefinition();
    }


    public Map<String, String> getAllParams() {
        return this.allParams;
    }

    public void setAllParams(Map<String, String> allParams) {
        this.allParams = allParams;
    }


    public String getParamValue(String paramName) {
        if (allParams != null) {
            return allParams.get(paramName);
        } else {
            return null;
        }
    }

    public void setHttpAction(HttpAction httpAction) {
        this.httpAction = httpAction;
    }


    public HttpAction getHttpAction() {
        return this.httpAction;
    }


    public String getRequestId() {
        return this.requestId;
    }
}

