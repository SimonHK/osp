/**
 * 版权声明：开源随便使用
 * 日    期：16-7-20
 */
package com.knowlegene.sample;

import com.nswt.osp.ThreadFerry;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class SampleThreadFerry implements ThreadFerry{


    public void doInSrcThread() {
        System.out.println("doInSrcThread:"+Thread.currentThread().getId());
    }


    public void doInDestThread() {
        System.out.println("doInSrcThread:"+Thread.currentThread().getId());
    }
}

