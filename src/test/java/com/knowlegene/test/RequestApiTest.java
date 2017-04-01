package com.knowlegene.test;

import com.nswt.osp.MessageFormat;
import com.nswt.osp.client.DefaultOspClient;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hongkai on 2016/12/22.
 */
public class RequestApiTest {

    public static final String SERVER_URL = "http://localhost:8989/geneapiserver/router";
    public static final String APP_KEY = "100001";
    public static final String APP_SECRET = "a4160d00-b083-40f9-a749-07aef8781d52";
    private DefaultOspClient ospClient = new DefaultOspClient(SERVER_URL, APP_KEY, APP_SECRET, MessageFormat.json);

    @Test
    public void test_view_Travel(){
        MultiValueMap<String, String> paramValues = new LinkedMultiValueMap<String, String>();
        //系统级参数
        paramValues.add("method", "view-travel");
        paramValues.add("appKey", APP_KEY);
        paramValues.add("appSecret", APP_SECRET);
        paramValues.add("v", "1.0");
        paramValues.add("format", "json");
        String sign = CopUtils.sign(paramValues.toSingleValueMap(), APP_SECRET);
        paramValues.add("sign", sign);
        //业务参数  不参与签名
        paramValues.add("travelPage", "3");

        String buildGetUrl = CopUtils.buildGetUrl(paramValues.toSingleValueMap(), SERVER_URL);
        String responseContent = new RestTemplate().getForObject(buildGetUrl, String.class, paramValues);
        System.out.println(responseContent);
    }

}