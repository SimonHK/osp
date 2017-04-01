/**
 * 版权声明：开源随便使用
 * 日    期：16-7-27
 */
package com.knowlegene.sample;

import com.nswt.osp.OspRequest;
import com.nswt.osp.annotation.ServiceMethod;
import com.nswt.osp.annotation.ServiceMethodBean;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
@ServiceMethodBean(version = "1.0")
public class LogoutService {

    @ServiceMethod(method = "user.logout")
    public Object logout(OspRequest request){
        return null;
    }
}

