/**
 *
 * 日    期：16-2-13
 */
package com.nswt.osp.security;

import com.nswt.osp.session.Session;

/**
 * <pre>
 *    安全控制控制器，决定用户是否有。
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public interface ServiceAccessController {

    /**
     * 服务方法是否向ISV开放
     * @param method
     * @param userId
     * @return
     */
    boolean isAppGranted(String appKey, String method, String version);

    /**
     *  服务方法是否向当前用户开放
     * @param ospRequestContext
     * @return
     */
    boolean isUserGranted(Session session,String method,String version);
}

