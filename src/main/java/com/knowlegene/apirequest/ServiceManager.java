package com.knowlegene.apirequest;

import com.knowlegene.apirequest.hongkaitest.hongkaitest.HongkaitestApiResponse;
import com.knowlegene.apirequest.hongkaitest.hongkaitest.HongkaitestAppiRequest;
import com.knowlegene.apirequest.hongkaitest.hongkaitest.model.HongkaitestApi;
import com.knowlegene.sample.AbstractUserService;
import com.knowlegene.sample.request.LogonRequest;
import com.nswt.osp.OspRequest;
import com.nswt.osp.ServiceMethodHandler;
import com.nswt.osp.annotation.*;

import java.util.*;

/**
 * Created by hongkai on 2017/6/26.
 */
//标注Osp的注解，使HongkaitestApiService成为一个Osp的服务
@ServiceMethodBean(version = "1.0")
public class ServiceManager extends AbstractUserService {

    @ServiceMethod(method = "service.list", version = "1.0", ignoreSign = IgnoreSignType.NO, needInSession = NeedInSessionType.NO, httpAction = HttpAction.GET)
    public Object servicelist(OspRequest ospRequest) throws Throwable {
        Map<String, ServiceMethodHandler> serviceMethodHandlers = ospRequest.getOspRequestContext().getOspContext().getAllServiceMethodHandlers();
        List serviceList = new ArrayList(serviceMethodHandlers.size());
        Object[] keys =  serviceMethodHandlers.keySet().toArray();
        Object[] values =  serviceMethodHandlers.values().toArray();
        ServiceObj serviceObj = null;
        int ii = 0;
        for (String servicekey : serviceMethodHandlers.keySet()){
            serviceObj = new ServiceObj();
            serviceObj.setServiceId(String.valueOf(ii));
            serviceObj.setServiceName(servicekey);
            serviceObj.setServiceNumber(String.valueOf(ii));
            serviceList.add(serviceObj);
            ii++;
        }
        return serviceList;
    }

    @Override
    public Object getSession(LogonRequest request) {
        return null;
    }
}
