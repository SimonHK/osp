/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-2
 */
package com.nswt.osp.event;

/**
 * <pre>
 *   检查是否支持特定的事件
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public interface SmartOspEventListener extends OspEventListener<OspEvent> {

    /**
     * 是否支持此事件
     *
     * @param eventType
     * @return
     */
    boolean supportsEventType(Class<? extends OspEvent> eventType);
}

