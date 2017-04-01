/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-4
 */
package com.nswt.osp.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class OspNamespaceHandler extends NamespaceHandlerSupport {


    public void init() {
        registerBeanDefinitionParser("annotation-driven", new AnnotationDrivenBeanDefinitionParser());
        registerBeanDefinitionParser("interceptors", new InterceptorsBeanDefinitionParser());
        registerBeanDefinitionParser("listeners", new ListenersBeanDefinitionParser());
        registerBeanDefinitionParser("sysparams", new SystemParameterNamesBeanDefinitionParser());
    }
}

