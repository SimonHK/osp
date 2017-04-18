/**
 * 版权声明：开源随便使用
 * 日    期：16-6-30
 */
package com.nswt.osp.client;

import com.knowlegene.sample.request.LogonRequest;
import com.knowlegene.sample.response.CreateUserResponse;
import com.knowlegene.sample.response.LogonResponse;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class DefaultOspClientTest {


/*
    @Test
    public void testPostWithSession() throws Exception {

    }*/
    public static void main(String [] args ){
        /*ogonRequest ospRequest = new LogonRequest();
        OspClient ospClient = new DefaultOspClient("http://localhost:8080/osp-1.0.0-SNAPSHOT/router", "00001", "abcdeabcdeabcdeabcdeabcde");
        CompositeResponse response = ospClient.buildClientRequest()
                .post(ospRequest, CreateUserResponse.class, "hongkaitest.list", "1.0");
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertTrue(response.getSuccessResponse() instanceof CreateUserResponse);*/
        /*OspClient ospClient = new DefaultOspClient("http://localhost:8080/osp-1.0.0-SNAPSHOT/router", "00001", "abcdeabcdeabcdeabcdeabcde");
        LogonRequest ospRequest = new LogonRequest();
        ospRequest.setUserName("tomson");
        ospRequest.setPassword("123456");
        //对服务发起调用并获得相应
        CompositeResponse response = ospClient.buildClientRequest()
                .get(ospRequest, LogonResponse.class, "hongkaitest.list", "1.0");
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertNotNull(response.getSuccessResponse());
        assertTrue(response.getSuccessResponse() instanceof LogonResponse);
        assertEquals(((LogonResponse) response.getSuccessResponse()).getSessionId(), "mockSessionId1");
        ospClient.setSessionId(((LogonResponse) response.getSuccessResponse()).getSessionId());*/
        String SERVER_URL = "http://127.0.0.1:8080/osp-1.0.0-SNAPSHOT/router";
        String APP_KEY = "00001";
        String APP_SECRET = "abcdeabcdeabcdeabcdeabcde";
         DefaultOspClient ospClient = new DefaultOspClient(SERVER_URL, APP_KEY, APP_SECRET);
        CompositeResponse response = ospClient.buildClientRequest()
                .addParam("userName", "tomson")
                .addParam("password", "123456", true)
                .get(LogonResponse.class, "user.getSession", "1.0");

        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertNotNull(response.getSuccessResponse());
        assertTrue(response.getSuccessResponse() instanceof LogonResponse);
        assertEquals(((LogonResponse) response.getSuccessResponse()).getSessionId(), "mockSessionId1");
    }
}

