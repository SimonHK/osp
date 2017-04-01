/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-29
 */
package com.nswt.osp.client;

import com.nswt.osp.request.OspConverter;


/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public interface OspClient {

    /**
     * 添加自定义的转换器
     *
     * @param ospConverter
     */
    void addOspConvertor(OspConverter ospConverter);

    /**
     * 设置method系统参数的参数名，下同
     *
     * @param paramName
     * @return
     */
    OspClient setAppKeyParamName(String paramName);

    /**
     * 设置sessionId的参数名
     *
     * @param paramName
     * @return
     */
    OspClient setSessionIdParamName(String paramName);

    /**
     * 设置method的参数名
     *
     * @param paramName
     * @return
     */
    OspClient setMethodParamName(String paramName);

    /**
     * 设置version的参数名
     *
     * @param paramName
     * @return
     */
    OspClient setVersionParamName(String paramName);

    /**
     * 设置format的参数名
     *
     * @param paramName
     * @return
     */
    OspClient setFormatParamName(String paramName);

    /**
     * 设置locale的参数名
     *
     * @param paramName
     * @return
     */
    OspClient setLocaleParamName(String paramName);

    /**
     * 设置sign的参数名
     *
     * @param paramName
     * @return
     */
    OspClient setSignParamName(String paramName);

    /**
     * 设置sessionId
     *
     * @param sessionId
     */
    void setSessionId(String sessionId);

    /**
     * 创建一个新的服务请求
     * @return
     */
    ClientRequest buildClientRequest();
}

