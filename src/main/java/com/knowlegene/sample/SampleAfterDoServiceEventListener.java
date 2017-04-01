/**
 * 版权声明：开源随便使用
 * 日    期：16-6-2
 */
package com.knowlegene.sample;

import com.nswt.osp.OspRequestContext;
import com.nswt.osp.event.AfterDoServiceEvent;
import com.nswt.osp.event.OspEventListener;
import com.nswt.osp.marshaller.MessageMarshallerUtils;

import java.util.Map;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class SampleAfterDoServiceEventListener implements OspEventListener<AfterDoServiceEvent> {


    public void onOspEvent(AfterDoServiceEvent ospEvent) {
        OspRequestContext ropRequestContext = ospEvent.getOspRequestContext();
        if(ropRequestContext != null){
            Map<String,String> allParams = ropRequestContext.getAllParams();
            String message = MessageMarshallerUtils.asUrlString(allParams);
            System.out.println("message("+ospEvent.getServiceEndTime()+")"+message);
        }
    }


    public int getOrder() {
        return 0;
    }
}

