/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-2
 */
package com.nswt.osp.event;

import java.util.EventListener;

/**
 * <pre>
 *    监听所有Osp框架的事件
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public interface OspEventListener<E extends OspEvent> extends EventListener {

    /**
     * 响应事件
     *
     * @param ospEvent
     */
    void onOspEvent(E ospEvent);

    /**
     * 执行的顺序号
     *
     * @return
     */
    int getOrder();
}

