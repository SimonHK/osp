/**
 * 版权声明： 开源随便使用
 * 日    期：16-4-26
 */
package com.nswt.osp;

/**
 * <pre>
 *    抽象拦截器，实现类仅需覆盖特定的方法即可。
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public abstract class AbstractInterceptor implements Interceptor {

    public void beforeService(OspRequestContext ropRequestContext) {
    }


    public void beforeResponse(OspRequestContext ropRequestContext) {
    }


    public boolean isMatch(OspRequestContext ropRequestContext) {
        return true;
    }

    /**
     * 放在拦截器链的最后
     *
     * @return
     */
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}

