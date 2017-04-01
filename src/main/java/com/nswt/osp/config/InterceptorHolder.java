/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-5
 */
package com.nswt.osp.config;

import com.nswt.osp.Interceptor;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class InterceptorHolder {

    private Interceptor interceptor;

    public InterceptorHolder(Interceptor interceptor) {
        this.interceptor = interceptor;
    }

    public Interceptor getInterceptor() {
        return interceptor;
    }
}

