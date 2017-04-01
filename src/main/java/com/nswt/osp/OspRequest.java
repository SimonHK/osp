/**
 *
 * 日    期：16-2-10
 */
package com.nswt.osp;

/**
 * <pre>
 *    OSP服务的请求对象，请求方法的入参必须是继承于该类。
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public interface OspRequest {

    /**
     * 获取服务方法的上下文
     *
     * @return
     */
    OspRequestContext getOspRequestContext();

}

