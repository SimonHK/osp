/**
 *
 * 日    期：16-2-13
 */
package com.nswt.osp.impl;

import com.nswt.osp.security.ServiceAccessController;
import com.nswt.osp.session.Session;

/**
 * <pre>
 * 功能说明：对调用的方法进行安全性检查
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class DefaultServiceAccessController implements ServiceAccessController {


    public boolean isAppGranted(String appKey, String method, String version) {
        return true;
    }


    public boolean isUserGranted(Session session, String method, String version) {
        return true;
    }
}

