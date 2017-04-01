/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-2
 */
package com.nswt.osp.event;

import org.springframework.aop.support.AopUtils;
import org.springframework.core.GenericTypeResolver;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class GenericOspEventAdapter implements SmartOspEventListener {

    private final OspEventListener delegate;

    public GenericOspEventAdapter(OspEventListener delegate) {
        this.delegate = delegate;
    }


    public boolean supportsEventType(Class<? extends OspEvent> eventType) {
        Class typeArg = GenericTypeResolver.resolveTypeArgument(this.delegate.getClass(), OspEventListener.class);
        if (typeArg == null || typeArg.equals(OspEvent.class)) {
            Class targetClass = AopUtils.getTargetClass(this.delegate);
            if (targetClass != this.delegate.getClass()) {
                typeArg = GenericTypeResolver.resolveTypeArgument(targetClass, OspEventListener.class);
            }
        }
        return (typeArg == null || typeArg.isAssignableFrom(eventType));
    }


    public void onOspEvent(OspEvent ropEvent) {
        this.delegate.onOspEvent(ropEvent);
    }



    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}

