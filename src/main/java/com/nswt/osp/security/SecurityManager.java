/**
 *
 * 日    期：16-2-13
 */
package com.nswt.osp.security;

import com.nswt.osp.OspRequestContext;
import com.nswt.osp.session.SessionManager;

/**
 * <pre>
 *   负责对请求数据、会话、业务安全、应用密钥安全进行检查并返回相应的错误
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public interface SecurityManager {

    /**
     * 对请求服务的上下文进行检查校验
     *
     * @param ospRequestContext
     * @return
     */
    MainError validateSystemParameters(OspRequestContext ospRequestContext);

    /**
     * 验证其它的事项：包括业务参数，业务安全性，会话安全等
     *
     * @param ospRequestContext
     * @return
     */
    MainError validateOther(OspRequestContext ospRequestContext);

    /**
     * 获取安全管理器
     *
     * @return
     */
    void setServiceAccessController(ServiceAccessController serviceAccessController);

    /**
     * 获取应用密钥管理器
     *
     * @return
     */
    void setAppSecretManager(AppSecretManager appSecretManager);

    /**
     * 设置会话管理器，以验证会话的合法性
     *
     * @return
     */
    void setSessionManager(SessionManager sessionManager);


    /**
     * 设置服务调度次数管理器
     * @param invokeTimesController
     */
    void setInvokeTimesController(InvokeTimesController invokeTimesController);

    /**
     * 文件上传控制器
     * @param fileUploadController
     */
    void setFileUploadController(FileUploadController fileUploadController);
}

