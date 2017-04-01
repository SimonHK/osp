/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-6
 */
package com.nswt.osp;

import com.nswt.osp.annotation.Temporary;

/**
 * <pre>
 *   所有请求对象应该通过扩展此抽象类实现
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public abstract class AbstractOspRequest implements OspRequest {

    @Temporary
    private OspRequestContext ospRequestContext;


    public OspRequestContext getOspRequestContext() {
        return ospRequestContext;
    }

    public final void setOspRequestContext(OspRequestContext ospRequestContext) {
        this.ospRequestContext = ospRequestContext;
    }
}

