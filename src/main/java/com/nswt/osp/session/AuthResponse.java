package com.nswt.osp.session;

/**
 *认证结果，如果认证成功，则设置{@link Session}，否则设置{@link #errorCode},{@link #errorCode}必须
 * 在OSP的国际化文件中定义。
 * @author : Admin
 * @date: 16-8-13
 */
public class AuthResponse {

    private Session ospSession;

    private boolean authenticated = false;

    private String errorCode;

    public Session getOspSession() {
        return ospSession;
    }

    public void setOspSession(Session logonSession) {
        this.ospSession = logonSession;
        this.authenticated = true;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
