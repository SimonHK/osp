/**
 * 版权声明：开源随便使用
 * 日    期：16-6-30
 */
package com.knowlegene.sample;

import com.nswt.osp.MessageFormat;
import com.nswt.osp.client.ClientRequest;
import com.nswt.osp.client.CompositeResponse;
import com.nswt.osp.client.DefaultOspClient;
import com.nswt.osp.request.UploadFile;
import com.nswt.osp.response.ErrorResponse;
import com.knowlegene.sample.converter.TelephoneConverter;
import com.knowlegene.sample.request.*;
import com.knowlegene.sample.response.CreateUserResponse;
import com.knowlegene.sample.response.LogonResponse;
import com.knowlegene.sample.response.UploadUserPhotoResponse;
import com.knowlegene.sample.response.UserListResponse;
import com.nswt.osp.security.MainErrorType;
import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class UserServiceClient {

    public static final String SERVER_URL = "http://localhost:8080/osp-1.0.0-SNAPSHOT/router";
    public static final String APP_KEY = "00001";
    public static final String APP_SECRET = "abcdeabcdeabcdeabcdeabcde";
    private DefaultOspClient ospClient = new DefaultOspClient(SERVER_URL, APP_KEY, APP_SECRET);

    {
        ospClient.setFormatParamName("messageFormat");
        ospClient.addOspConvertor(new TelephoneConverter());
    }


    @BeforeClass
    public void createSession() {
        LogonRequest ospRequest = new LogonRequest();
        ospRequest.setUserName("tomson");
        ospRequest.setPassword("123456");
        //对服务发起调用并获得相应
        CompositeResponse response = ospClient.buildClientRequest()
                                   .get(ospRequest, LogonResponse.class, "user.getSession", "1.0");
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertNotNull(response.getSuccessResponse());
        assertTrue(response.getSuccessResponse() instanceof LogonResponse);
        assertEquals(((LogonResponse) response.getSuccessResponse()).getSessionId(), "mockSessionId1");
        ospClient.setSessionId(((LogonResponse) response.getSuccessResponse()).getSessionId());
    }

    @Test
    public void createSessionWithParamMap() {
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

    @Test
    public void addUser() {
        CompositeResponse response = ospClient.buildClientRequest()
                .addParam("userName", "tomson")
                .addParam("password", "123456", true)
                .get(LogonResponse.class, "user.getSession", "1.0");
        String sessionId = ((LogonResponse) response.getSuccessResponse()).getSessionId();
        ospClient.setSessionId(sessionId);


        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUserName("katty");
        createUserRequest.setSalary(2500L);

        Telephone telephone = new Telephone();
        telephone.setZoneCode("010");
        telephone.setTelephoneCode("12345678");
        createUserRequest.setTelephone(telephone);

        //add1
        response = ospClient.buildClientRequest()
                .post(createUserRequest, CreateUserResponse.class, "user.add", "1.0");
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertTrue(response.getSuccessResponse() instanceof CreateUserResponse);

        //add2
        response = ospClient.buildClientRequest()
                .post(createUserRequest, CreateUserResponse.class, "user.add", "1.0");
    }

    @Test
    public void addUserUseParamMap() {
        CompositeResponse response = ospClient.buildClientRequest()
                .addParam("userName", "tomson")
                .addParam("password", "123456", true)
                .get(LogonResponse.class, "user.getSession", "1.0");
        String sessionId = ((LogonResponse) response.getSuccessResponse()).getSessionId();
        ospClient.setSessionId(sessionId);


        ClientRequest cr2 = ospClient.buildClientRequest();
        cr2.addParam("userName", "katty");
        cr2.addParam("salary", 2500L);
        Telephone telephone = new Telephone();
        telephone.setZoneCode("010");
        telephone.setTelephoneCode("12345678");
        cr2.addParam("telephone", telephone);

        response = cr2.post(CreateUserResponse.class, "user.add", "1.0");
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertTrue(response.getSuccessResponse() instanceof CreateUserResponse);
    }


    @Test
    public void testAddUserByVersion3() {
        CreateUserRequest ospRequest = new CreateUserRequest();
        ospRequest.setUserName("tomson");
        ospRequest.setSalary(2500L);
        ospClient.setMessageFormat(MessageFormat.xml);

        CompositeResponse response = ospClient.buildClientRequest()
                .post(ospRequest, CreateUserResponse.class, "user.add", "3.0");
        assertNotNull(response);
        assertFalse(response.isSuccessful());
        assertNull(response.getSuccessResponse());
        assertNotNull(response.getErrorResponse());
        assertTrue(response.getErrorResponse() instanceof ErrorResponse);
        assertEquals(response.getErrorResponse().getCode(), MainErrorType.UNSUPPORTED_VERSION.value());
    }

    @Test
    public void testFileUpload() throws Throwable {
        ClientRequest cr = ospClient.buildClientRequest();

        UploadUserPhotoRequest request = new UploadUserPhotoRequest();
        ClassPathResource resource = new ClassPathResource("photo.png");
        UploadFile uploadFile = new UploadFile(resource.getFile());
        request.setPhoto(uploadFile);
        request.setUserId("1");
        ospClient.setMessageFormat(MessageFormat.xml);

        CompositeResponse response = ospClient.buildClientRequest()
                .post(request, UploadUserPhotoResponse.class, "user.upload.photo", "1.0");
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertTrue(response.getSuccessResponse() instanceof UploadUserPhotoResponse);
        assertEquals(((UploadUserPhotoResponse) response.getSuccessResponse()).getFileType(), "png");
        assertEquals(((UploadUserPhotoResponse) response.getSuccessResponse()).getLength(), uploadFile.getContent().length);
    }

    @Test
    public void testServiceXmlRequestAttr() throws Throwable {
        CreateUserRequest request = new CreateUserRequest();
        request.setUserName("tomson");
        request.setLocked(true);
        request.setSalary(2500L);
        Address address = new Address();
        address.setZoneCode("0001");
        address.setDoorCode("002");
        Street street1 = new Street();
        street1.setName("street1");
        street1.setNo("001");
        Street street2 = new Street();
        street2.setName("street2");
        street2.setNo("002");
        ArrayList<Street> streets = new ArrayList<Street>();
        streets.add(street1);
        streets.add(street2);
        address.setStreets(streets);
        request.setAddress(address);

        ospClient.setMessageFormat(MessageFormat.xml);
        CompositeResponse response = ospClient.buildClientRequest()
                .post(request, CreateUserResponse.class, "user.add", "1.0");
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertTrue(response.getSuccessResponse() instanceof CreateUserResponse);
    }

    @Test
    public void testServiceJsonRequestAttr() throws Throwable {
        ospClient.setMessageFormat(MessageFormat.json);
        CreateUserRequest request = new CreateUserRequest();
        request.setUserName("tomson");
        request.setSalary(2500L);
        Address address = new Address();
        address.setZoneCode("0001");
        address.setDoorCode("002");
        Street street1 = new Street();
        street1.setName("street1");
        street1.setNo("001");
        Street street2 = new Street();
        street2.setName("street2");
        street2.setNo("002");
        ArrayList<Street> streets = new ArrayList<Street>();
        streets.add(street1);
        streets.add(street2);
        address.setStreets(streets);
        request.setAddress(address);

        CompositeResponse response = ospClient.buildClientRequest()
                .post(request, CreateUserResponse.class, "user.add", "1.0");
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertTrue(response.getSuccessResponse() instanceof CreateUserResponse);
    }

    @Test
    public void testUserList() throws Throwable {
        ospClient.setMessageFormat(MessageFormat.json);
        CompositeResponse response = ospClient.buildClientRequest().get(UserListResponse.class,"user.list", "1.0");
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertTrue(response.getSuccessResponse() instanceof UserListResponse);
    }


    @Test
    public void testCustomConverter() {
        ospClient.addOspConvertor(new TelephoneConverter());
        CreateUserRequest request = new CreateUserRequest();
        request.setUserName("tomson");
        request.setSalary(2500L);
        Telephone telephone = new Telephone();
        telephone.setZoneCode("0592");
        telephone.setTelephoneCode("12345678");

        CompositeResponse response = ospClient.buildClientRequest().post(request, CreateUserResponse.class, "user.add", "1.0");

        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertTrue(response.getSuccessResponse() instanceof CreateUserResponse);
    }
}

