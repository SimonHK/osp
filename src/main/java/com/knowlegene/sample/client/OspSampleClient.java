/**
 * 版权声明：开源随便使用
 * 日    期：16-8-4
 */
package com.knowlegene.sample.client;

import com.nswt.osp.client.ClientRequest;
import com.nswt.osp.client.CompositeResponse;
import com.nswt.osp.client.DefaultOspClient;
import com.knowlegene.sample.request.LogonRequest;
import com.knowlegene.sample.converter.TelephoneConverter;
import com.knowlegene.sample.response.LogonResponse;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class OspSampleClient {

    public static final String SERVER_URL = "http://localhost:8088/router";

    private DefaultOspClient ospClient ;

    /**
     * 创建客户端对象
     * @param appKey
     * @param secret
     */
    public OspSampleClient(String appKey, String secret) {
        ospClient = new DefaultOspClient(SERVER_URL, appKey, secret);
        ospClient.setFormatParamName("messageFormat");
        ospClient.addOspConvertor(new TelephoneConverter());
    }

    /**
     * 登录系统
     *
     * @return
     */
    public String logon(String userName, String password) {
        LogonRequest ospRequest = new LogonRequest();
        ospRequest.setUserName("tomson");
        ospRequest.setPassword("123456");
        CompositeResponse response = ospClient.buildClientRequest().get(ospRequest, LogonResponse.class, "user.logon", "1.0");
        String sessionId = ((LogonResponse) response.getSuccessResponse()).getSessionId();
        ospClient.setSessionId(sessionId);
        return sessionId;
    }

    public void logout() {
        ospClient.buildClientRequest().get(LogonResponse.class, "user.logout", "1.0");
    }

    public ClientRequest buildClientRequest(){
        return ospClient.buildClientRequest();
    }
}

