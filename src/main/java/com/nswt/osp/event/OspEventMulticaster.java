/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-2
 */
package com.nswt.osp.event;

/**
 * <pre>
 *   注册事件监听器，发布事件
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public interface OspEventMulticaster {

    /**
     * Add a listener to be notified of all events.
     *
     * @param listener the listener to add
     */
    void addOspListener(OspEventListener listener);

    /**
     * Remove a listener from the notification list.
     *
     * @param listener the listener to remove
     */
    void removeOspListener(OspEventListener listener);

    /**
     * Remove all listeners registered with this multicaster.
     * <p>After a remove call, the multicaster will perform no action
     * on event notification until new listeners are being registered.
     */
    void removeAllOspListeners();

    /**
     * Multicast the given application event to appropriate listeners.
     *
     * @param event the event to multicast
     */
    void multicastEvent(OspEvent event);
}

