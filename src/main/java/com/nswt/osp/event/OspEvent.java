/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-2
 */
package com.nswt.osp.event;

import com.nswt.osp.OspContext;

import java.util.EventObject;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public abstract class OspEvent extends EventObject {

    private OspContext ospContext;

    public OspEvent(Object source, OspContext ospContext) {
        super(source);
        this.ospContext = ospContext;
    }

    public OspContext getOspContext() {
        return ospContext;
    }
}

