/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-1
 */
package com.nswt.osp;

/**
 * <pre>
 *    更改请求对象创建{@link OspRequestContext}实例,子类可以根据多种传输协议定义自己的创建器。
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public interface RequestContextBuilder {
    /**
     * 根据reqeuest及response请求响应对象，创建{@link OspRequestContext}实例。绑定系统参数，请求对象
     * @param ospContext
     * @param request
     * @param response
     * @return
     */
    OspRequestContext buildBySysParams(OspContext ospContext, Object request, Object response);

    /**
     * 绑定业务参数
     *
     * @param ospRequestContext
     */
    OspRequest buildOspRequest(OspRequestContext ospRequestContext);
}

