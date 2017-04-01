/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-1
 */
package com.nswt.osp.event;

import com.nswt.osp.OspRequestContext;

/**
 * <pre>
 *    在执行服务方法之前产生的事件
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class PreDoServiceEvent extends OspEvent {

    private OspRequestContext ospRequestContext;

    public PreDoServiceEvent(Object source, OspRequestContext ospRequestContext) {
        super(source, ospRequestContext.getOspContext());
        this.ospRequestContext = ospRequestContext;
    }

    public OspRequestContext getOspRequestContext() {
        return ospRequestContext;
    }

    public long getServiceBeginTime() {
        return ospRequestContext.getServiceBeginTime();
    }
}

