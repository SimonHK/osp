/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-1
 */
package com.nswt.osp.event;

import com.nswt.osp.OspContext;

/**
 * <pre>
 *   在Osp框架初始化后产生的事件
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class AfterStartedOspEvent extends OspEvent {

    public AfterStartedOspEvent(Object source, OspContext ospContext) {
        super(source, ospContext);
    }

}

