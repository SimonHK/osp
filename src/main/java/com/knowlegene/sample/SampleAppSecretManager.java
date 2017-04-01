package com.knowlegene.sample; /**
 * 版权声明：开源随便使用
 * 日    期：16-5-25
 */

import com.nswt.osp.security.AppSecretManager;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 功能说明：签名码获取
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class SampleAppSecretManager implements AppSecretManager {

    private static Map<String, String> appKeySecretMap = new HashMap<String, String>();

    static {
        appKeySecretMap.put("00001", "abcdeabcdeabcdeabcdeabcde");
        appKeySecretMap.put("00002","abcdeab6667788uueabcdeaaaaa");
        appKeySecretMap.put("00003","abcdeabcdeabcdeabcdeaaaaa");
    }


    public String getSecret(String appKey) {
        System.out.println("use SampleAppSecretManager!");
        return appKeySecretMap.get(appKey);
    }


    public boolean isValidAppKey(String appKey) {
        return getSecret(appKey) != null;
    }
}

