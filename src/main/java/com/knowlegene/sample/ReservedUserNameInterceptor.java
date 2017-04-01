/**
 * 版权声明：开源随便使用
 * 日    期：16-4-26
 */
package com.knowlegene.sample;

import com.nswt.osp.AbstractInterceptor;
import com.nswt.osp.OspRequestContext;
import com.knowlegene.sample.response.InterceptorResponse;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *    该拦截器仅对method为“user.add”进行拦截，你可以在{@link #isMatch(OspRequestContext)}方法中定义拦截器的匹配规则。
 *  你可以通过{@link OspRequestContext#getServiceMethodDefinition()}获取服务方法的注解信息，通过这些信息进行拦截匹配规则
 *  定义。
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */

@Component
public class ReservedUserNameInterceptor extends AbstractInterceptor {

    /**
     * 在数据绑定后，服务方法调用前执行该拦截方法
     *
     * @param ospRequestContext
     */
    public void beforeService(OspRequestContext ospRequestContext) {
        System.out.println("beforeService ...");

        if ("jhonson".equals(ospRequestContext.getParamValue("userName"))) {
            InterceptorResponse response = new InterceptorResponse();
            response.setTestField("the userName can't be jhonson!");
            //设置了RopResponse后，后续的服务将不执行，直接返回这个RopResponse响应
            ospRequestContext.setOspResponse(response);
        }
    }

    /**
     * 在服务执行完成后，响应返回前执行该拦截方法
     *
     * @param ospRequestContext
     */

    public void beforeResponse(OspRequestContext ospRequestContext) {
        System.out.println("beforeResponse ...");
    }

    /**
     * 对method为user.add的方法进行拦截，你可以通过methodContext中的信息制定拦截方案
     *
     * @param ospRequestContext
     * @return
     */

    public boolean isMatch(OspRequestContext ospRequestContext) {
        return "user.add".equals(ospRequestContext.getMethod());
    }
}

