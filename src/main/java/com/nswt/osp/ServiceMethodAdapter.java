/**
 *
 * 日    期：16-2-11
 */
package com.nswt.osp;

/**
 * <pre>
 *    通过该适配器以统一的方式调用Osp方法
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public interface ServiceMethodAdapter {
    /**
     * 调用服务方法
     *
     * @param ospRequest
     * @return
     */
    Object invokeServiceMethod(OspRequest ospRequest);

}

