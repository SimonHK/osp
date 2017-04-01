/**
 * 版权声明：开源随便使用
 * 日    期：16-6-30
 */
package com.nswt.osp.client;

import org.testng.annotations.Test;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class DefaultOspClientTest {

    private OspClient ospClient = new DefaultOspClient("http://localhost:8088/router", "00001", "abcdeabcdeabcdeabcdeabcde");

    @Test
    public void testPostWithSession() throws Exception {

    }
}

