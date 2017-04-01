/**
 * 版权声明：开源随便使用
 * 日    期：16-6-2
 */
package com.knowlegene.sample;

import com.nswt.osp.event.AfterStartedOspEvent;
import com.nswt.osp.event.OspEventListener;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class SamplePostInitializeEventListener implements OspEventListener<AfterStartedOspEvent> {


    public void onOspEvent(AfterStartedOspEvent ospOspEvent) {
        System.out.println("execute SamplePostInitializeEventListener!");
    }


    public int getOrder() {
        return 0;
    }
}

