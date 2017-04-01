/**
 * 日    期：16-2-11
 */
package com.nswt.osp.impl;

import com.nswt.osp.OspRequest;
import com.nswt.osp.OspRequestContext;
import com.nswt.osp.ServiceMethodAdapter;
import com.nswt.osp.ServiceMethodHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.InvocationTargetException;


/**
 * <pre>
 *    通过该服务方法适配器调用目标的服务方法
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class AnnotationServiceMethodAdapter implements ServiceMethodAdapter {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    /**
     * 调用Osp服务方法
     *
     * @param ospRequest
     * @return
     */
    public Object invokeServiceMethod(OspRequest ospRequest) {
        try {
            OspRequestContext ospRequestContext = ospRequest.getOspRequestContext();
            //分析上下文中的错误
            ServiceMethodHandler serviceMethodHandler = ospRequestContext.getServiceMethodHandler();
            if (logger.isDebugEnabled()) {
                logger.debug("执行" + serviceMethodHandler.getHandler().getClass() +
                        "." + serviceMethodHandler.getHandlerMethod().getName());
            }
            if (serviceMethodHandler.isHandlerMethodWithParameter()) {
                return serviceMethodHandler.getHandlerMethod().invoke(
                        serviceMethodHandler.getHandler(),ospRequest);
            } else {
                return serviceMethodHandler.getHandlerMethod().invoke(serviceMethodHandler.getHandler());
            }
        } catch (Throwable e) {
            if (e instanceof InvocationTargetException) {
                InvocationTargetException inve = (InvocationTargetException) e;
                throw new RuntimeException(inve.getTargetException());
            } else {
                throw new RuntimeException(e);
            }
        }
    }

}

