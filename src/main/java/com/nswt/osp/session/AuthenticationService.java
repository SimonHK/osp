/**
 * Copyright： 版权所有 违者必究 2013
 */
package com.nswt.osp.session;

/**
 * 其中T是登录请求的类,而R是注销请求的类
 * @author : Admin
 * @date: 16-10-16
 */

import com.nswt.osp.OspRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public abstract class AuthenticationService<T extends OspRequest,R extends OspRequest> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 获取应用对应的认证管理器
     * @param appkey
     * @return
     */
    public abstract AuthenticationManager getAuthenticationManager(String appkey);

    /**
     * 该方法在子类在实现,并打上@ServiceMethod注解,作为登录的服务方法
     * @param logonRequest
     * @return
     */
    public abstract  Object logon(T logonRequest);


    /**
     * 该方法在子类在实现,并打上@ServiceMethod注解,作为注销的服务方法
     * @param logoutRequest
     * @return
     */
    public abstract Object logout(R logoutRequest);

    /**
     * 对登录请求进行认证,在子类的{@link #logon(OspRequest)}调用.
     * @param request
     * @return
     */
    protected AuthResponse  authentication(T request) {
        String appKey = request.getOspRequestContext().getAppKey();
        AuthenticationManager authenticationManager = getAuthenticationManager(appKey);
        AuthRequest authRequest = toAuthRequest(request);
        return authenticationManager.authenticate(authRequest);
    }


    /**
     * 将{@code logonRequest}转换为{@link AuthRequest}
     * @param logonRequest
     * @return
     */
    protected abstract AuthRequest toAuthRequest(Object logonRequest);

    /**
     * 该方法在子类的{@link #logout(OspRequest)}方法中调用.
     * @param logoutRequest
     * @return
     */
    protected  void removeSession(R logoutRequest){
        logoutRequest.getOspRequestContext().removeSession();
    }

    private  String uuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().toUpperCase();
    }

}
