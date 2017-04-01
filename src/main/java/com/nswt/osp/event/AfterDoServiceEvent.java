/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-2
 */
package com.nswt.osp.event;

import com.nswt.osp.OspRequestContext;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class AfterDoServiceEvent extends OspEvent {

    private OspRequestContext ospRequestContext;

    public AfterDoServiceEvent(Object source, OspRequestContext ropRequestContext) {
        super(source, ropRequestContext.getOspContext());
        this.ospRequestContext = ospRequestContext;
    }

    public long getServiceBeginTime() {
        return ospRequestContext.getServiceBeginTime();
    }

    public long getServiceEndTime() {
        return ospRequestContext.getServiceEndTime();
    }

    public OspRequestContext getOspRequestContext() {
        return ospRequestContext;
    }
}

