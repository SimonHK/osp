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

    public static final String SERVER_URL = "http://localhost:8080/osp-1.0.0-SNAPSHOT/router";
    public static final String APP_KEY = "00001";
    public static final String APP_SECRET = "abcdeabcdeabcdeabcdeabcde";
   /* private DefaultOspClient ospClient = new DefaultOspClient(SERVER_URL, APP_KEY, APP_SECRET, MessageFormat.json);

    @Test
    public void test_view_Travel(){
        MultiValueMap<String, String> paramValues = new LinkedMultiValueMap<String, String>();
        //系统级参数
        paramValues.add("method", "allrelationinfo.list");
        paramValues.add("appKey", APP_KEY);
        paramValues.add("appSecret", APP_SECRET);
        paramValues.add("v", "1.0");
        paramValues.add("format", "json");
        String sign = CopUtils.sign(paramValues.toSingleValueMap(), APP_SECRET);
        paramValues.add("sign", sign);
        //业务参数  不参与签名
        paramValues.add("travelPage", "3");

        String buildGetUrl = CopUtils.buildGetUrl(paramValues.toSingleValueMap(), SERVER_URL);
        System.out.println("BuildGetUrl:"+buildGetUrl);
        String responseContent = new RestTemplate().getForObject(buildGetUrl, String.class, paramValues);
        System.out.println(responseContent);
    }
*/
}