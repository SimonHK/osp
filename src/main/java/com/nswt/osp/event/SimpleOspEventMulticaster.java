/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-2
 */
package com.nswt.osp.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class SimpleOspEventMulticaster extends AbstractOspEventMulticaster {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private Executor executor;


    public void multicastEvent(final OspEvent event) {
        try {
            for (final OspEventListener listener : getOspEventListeners(event)) {
                Executor executor = getExecutor();
                if (executor != null) {
                    executor.execute(new Runnable() {

                        public void run() {
                            listener.onOspEvent(event);
                        }
                    });
                } else {
                    listener.onOspEvent(event);
                }
            }
        } catch (Exception e) {
            logger.error("处理"+event.getClass().getName()+"事件发生异常",e);
        }
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }
}

