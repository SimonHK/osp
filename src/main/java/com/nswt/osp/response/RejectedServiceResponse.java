/**
 * 版权声明： 开源随便使用
 * 日    期：16-8-2
 */
package com.nswt.osp.response;

import com.nswt.osp.OspRequestContext;
import com.nswt.osp.security.MainError;
import com.nswt.osp.security.MainErrorType;
import com.nswt.osp.security.MainErrors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <pre>
 *   当服务平台资源耗尽（超过最大线程数且列队排满后）
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "error")
public class RejectedServiceResponse extends ErrorResponse  {

    public RejectedServiceResponse() {
    }

    public RejectedServiceResponse(OspRequestContext context) {
        MainError mainError = MainErrors.getError(MainErrorType.FORBIDDEN_REQUEST, context.getLocale(),
                                                  context.getMethod(),context.getVersion());
        setMainError(mainError);
    }
}

