/**
 * 版权声明：开源随便使用
 * 日    期：16-7-17
 */
package com.knowlegene.sample;

import com.nswt.osp.OspRequestContext;
import com.nswt.osp.event.PreDoServiceEvent;
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
public class SamplePreDoServiceEventListener implements OspEventListener<PreDoServiceEvent> {


    public void onOspEvent(PreDoServiceEvent ospEvent) {
        OspRequestContext ospRequestContext = ospEvent.getOspRequestContext();
        if(ospRequestContext != null){
            Map<String,String> allParams = ospRequestContext.getAllParams();
            String message = MessageMarshallerUtils.asUrlString(allParams);
            System.out.println("message("+ospEvent.getServiceBeginTime()+")"+message);
        }
    }


    public int getOrder() {
        return 1;
    }
}

