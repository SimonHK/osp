/**
 * 版权声明：开源随便使用
 * 日    期：16-6-6
 */
package com.nswt.osp.other;

import com.nswt.osp.OspRequest;
import com.nswt.osp.AbstractOspRequest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class ClassTest {

    @Test
    public void testAssignableFrom() {
        assertTrue(!MyRopRequest.class.isAssignableFrom(OspRequest.class));
        assertTrue(!MyRopRequest.class.isAssignableFrom(AbstractOspRequest.class));
        assertTrue(AbstractOspRequest.class.isAssignableFrom(MyRopRequest.class));
    }

    @Test
    public void modeInt() {
        int len = 16 - 1;
        for (int i = 0; i < 100; i++) {
            int i1 = i & len;
            System.out.println("i:" + i1);
            assertTrue(i1 <= len);
        }
    }

    private class MyRopRequest extends AbstractOspRequest {

    }

}

