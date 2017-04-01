/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-5
 */
package com.nswt.osp.event;

import com.nswt.osp.OspContext;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class PreCloseOspEvent extends OspEvent {
    public PreCloseOspEvent(Object source, OspContext ospContext) {
        super(source, ospContext);
    }
}

