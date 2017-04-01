package com.nswt.osp.session;

/**
 * @author : Admin
 * @date: 16-8-13
 */
public abstract class AbstractAuthRequest implements AuthRequest {

    private Object detail;


    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

}
