/**
 * 版权声明： 开源随便使用
 * 日    期：16-7-30
 */
package com.nswt.osp.security;

import com.nswt.osp.session.Session;

/**
 * <pre>
 *    默认的实现
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class DefaultInvokeTimesController implements InvokeTimesController {


    public void caculateInvokeTimes(String appKey, Session session) {
    }


    public boolean isUserInvokeLimitExceed(String appKey, Session session) {
        return false;
    }


    public boolean isSessionInvokeLimitExceed(String appKey, String sessionId) {
        return false;
    }


    public boolean isAppInvokeLimitExceed(String appKey) {
        return false;
    }


    public boolean isAppInvokeFrequencyExceed(String appKey) {
        return false;
    }
}

