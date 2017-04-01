/**
 * 版权声明：开源随便使用
 * 日    期：16-6-8
 */
package com.nswt.osp.annotation;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class HttpActionTest {

    @Test
    public void testValueOf() {
        assertEquals(HttpAction.valueOf("GET"), HttpAction.GET);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void invalidValueOf() {
        assertEquals(HttpAction.valueOf("get"), HttpAction.GET);
    }
}

