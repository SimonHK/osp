package com.knowlegene.apirequest.hongkaitest.hongkaitest;

import com.knowlegene.apirequest.hongkaitest.hongkaitest.model.HongkaitestApi;
import com.knowlegene.apiservice.service.hongkaitest.hongkaitest.HongkaitestManager;
import com.knowlegene.sample.AbstractUserService;
import com.knowlegene.sample.request.LogonRequest;
import com.knowlegene.sample.response.LogonResponse;
import com.nswt.osp.annotation.*;
import com.nswt.osp.session.SimpleSession;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明： hongkaitest
 * 创建人：hongkai
 * 创建时间：2016-12-21
 * 邮箱地址：18611949252@163.com
 */

//标注Osp的注解，使HongkaitestApiService成为一个Osp的服务
@ServiceMethodBean(version = "1.0")
public class HongkaitestApiService extends AbstractUserService {

    @Resource(name = "hongkaitestService")
    private HongkaitestManager hongkaitestService;

    //使该服务方法成为一个webService的方法
    @ServiceMethod(method = "hongkaitest.getSession", version = "1.0", needInSession = NeedInSessionType.NO)
    public Object getSession(LogonRequest request) {
        //创建一个会话
        SimpleSession session = new SimpleSession();
        session.setAttribute("userName", request.getUserName());
        request.getOspRequestContext().addSession("mockSessionId1", session);
        //返回响应
        LogonResponse logonResponse = new LogonResponse();
        logonResponse.setSessionId("mockSessionId1");
        return logonResponse;
    }

    @ServiceMethod(method = "hongkaitest.list", version = "1.0", ignoreSign = IgnoreSignType.NO, needInSession = NeedInSessionType.NO, httpAction = HttpAction.GET)
    public Object hongkaitestList(HongkaitestAppiRequest ropRequest) throws Throwable {
        String userId = ropRequest.getOspRequestContext().getParamValue("travelPage");
        String travl = ropRequest.getTravelPage();
        HongkaitestApi hongkaitestApi = new HongkaitestApi();
        hongkaitestApi.setHONGKAITEST_ID("100021");
        hongkaitestApi.setTTT("222222");
        hongkaitestApi.setTTT3("sdfsdfsdf");
        List<HongkaitestApi> hongkaitestList = new ArrayList<>();
        hongkaitestList.add(0,hongkaitestApi);
        HongkaitestApiResponse hongkaitestApiResponse = new HongkaitestApiResponse();
        hongkaitestApiResponse.setFooList(hongkaitestList);
        return hongkaitestList;
    }
}
