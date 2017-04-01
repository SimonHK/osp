/**
 * 版权声明： 开源随便使用
 * 日    期：16-8-7
 */
package com.nswt.osp.client;

import com.nswt.osp.OspRequest;
import com.nswt.osp.annotation.IgnoreSign;

/**
 * <pre>
 *   每个请求对应一个ClientRequest对象
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public interface ClientRequest {

    /**
     * 添加请求参数,默认需要签名，如果类已经标注了{@link IgnoreSign}则始终不加入签名
     * @param paramName
     * @param paramValue
     * @return
     */
    ClientRequest addParam(String paramName,Object paramValue);

    /**
     * 添加请求参数,如果needSign=false表示不参于签名
     * @param paramName
     * @param paramValue
     * @param needSign
     * @return
     */
    ClientRequest addParam(String paramName,Object paramValue,boolean needSign);

    /**
     * 清除参数列表
     * @return
     */
    ClientRequest clearParam();

    /**
     * 使用POST发起请求
     * @param ospResponseClass
     * @param methodName
     * @param version
     * @param <T>
     * @return
     */
    <T> CompositeResponse post(Class<T> ospResponseClass, String methodName, String version);

    /**
     * 直接使用 ospRequest发送请求
     * @param ospRequest
     * @param ospResponseClass
     * @param methodName
     * @param version
     * @param <T>
     * @return
     */
    <T> CompositeResponse post(OspRequest ospRequest, Class<T> ospResponseClass, String methodName, String version);

    /**
     * 使用GET发送服务请求
     * @param ospResponseClass
     * @param methodName
     * @param version
     * @param <T>
     * @return
     */
    <T> CompositeResponse get(Class<T> ospResponseClass, String methodName, String version);

    /**
     * 使用GET发送ospRequest的请求
     * @param ospRequest
     * @param ospResponseClass
     * @param methodName
     * @param version
     * @param <T>
     * @return
     */
    <T> CompositeResponse get(OspRequest ospRequest, Class<T> ospResponseClass, String methodName, String version);
}

