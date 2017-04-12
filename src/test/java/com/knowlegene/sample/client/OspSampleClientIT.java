/**
 * 版权声明：开源随便使用
 * 日    期：16-8-4
 */
package com.knowlegene.sample.client;

import com.nswt.osp.client.CompositeResponse;
import com.knowlegene.sample.request.CreateUserRequest;
import com.knowlegene.sample.request.Telephone;
import com.knowlegene.sample.response.CreateUserResponse;
import org.testng.annotations.Test;

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
public class OspSampleClientIT {
    public static final String APP_KEY = "00001";
    public static final String APP_SECRET = "abcdeabcdeabcdeabcdeabcde";

    OspSampleClient ropSampleClient = new OspSampleClient(APP_KEY,APP_SECRET);

    @Test
    public void testLogon() throws Exception {
        String sessionId = ropSampleClient.logon("tomson", "123456");
        assertNotNull(sessionId);
    }

    @Test
    public void testLogout() throws Exception {
        ropSampleClient.logout();
    }

    @Test
    public void addUser() {

        ropSampleClient.logon("tomson", "123456");

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUserName("katty");
        createUserRequest.setSalary(2500L);

        Telephone telephone = new Telephone();
        telephone.setZoneCode("010");
        telephone.setTelephoneCode("12345678");
        createUserRequest.setTelephone(telephone);

        CompositeResponse response = ropSampleClient.buildClientRequest()
                                                      .post(createUserRequest, CreateUserResponse.class, "user.add", "1.0");
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertTrue(response.getSuccessResponse() instanceof CreateUserResponse);
    }
}

