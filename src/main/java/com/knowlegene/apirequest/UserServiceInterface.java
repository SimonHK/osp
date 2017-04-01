/**
 * Copyright：中软海晟信息科技有限公司 版权所有 违者必究 2014 
 */
package com.knowlegene.apirequest;

import com.nswt.osp.annotation.NeedInSessionType;
import com.nswt.osp.annotation.ServiceMethod;
import com.knowlegene.sample.request.LogonRequest;

/**
 * @author : Admin
 * @date: 14-3-6
 */
public interface UserServiceInterface {

    @ServiceMethod(method = "user.getSession",version = "1.0",needInSession = NeedInSessionType.NO)
    Object getSession(LogonRequest request);
}
