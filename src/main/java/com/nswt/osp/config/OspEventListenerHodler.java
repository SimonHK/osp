/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-5
 */
package com.nswt.osp.config;

import com.nswt.osp.event.OspEventListener;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class OspEventListenerHodler {

    private OspEventListener ospEventListener;

    public OspEventListenerHodler(OspEventListener ospEventListener) {
        this.ospEventListener = ospEventListener;
    }

    public OspEventListener getOspEventListener() {
        return ospEventListener;
    }
}

