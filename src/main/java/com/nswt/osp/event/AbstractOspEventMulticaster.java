/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-2
 */
package com.nswt.osp.event;

import java.util.*;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public abstract class AbstractOspEventMulticaster implements OspEventMulticaster {

    private Set<OspEventListener> ospEventListeners = new HashSet<OspEventListener>();

    private static final Map<Class<? extends OspEvent>, ListenerRegistry> cachedOspEventListeners =
            new HashMap<Class<? extends OspEvent>, ListenerRegistry>();


    public void removeAllOspListeners() {
        ospEventListeners.clear();
    }


    public void addOspListener(OspEventListener listener) {
        ospEventListeners.add(listener);
    }


    public void removeOspListener(OspEventListener listener) {
        ospEventListeners.remove(listener);
    }

    protected List<OspEventListener> getOspEventListeners(OspEvent event) {
        Class<? extends OspEvent> eventType = event.getClass();
        if (!cachedOspEventListeners.containsKey(eventType)) {
            LinkedList<OspEventListener> allListeners = new LinkedList<OspEventListener>();
            if (ospEventListeners != null && ospEventListeners.size() > 0) {
                for (OspEventListener ospEventListener : ospEventListeners) {
                    if (supportsEvent(ospEventListener, eventType)) {
                        allListeners.add(ospEventListener);
                    }
                }
                sortOspEventListener(allListeners);
            }
            ListenerRegistry listenerRegistry = new ListenerRegistry(allListeners);
            cachedOspEventListeners.put(eventType, listenerRegistry);
        }
        return cachedOspEventListeners.get(eventType).getOspEventListeners();
    }

    protected boolean supportsEvent(
            OspEventListener listener, Class<? extends OspEvent> eventType) {
        SmartOspEventListener smartListener = (listener instanceof SmartOspEventListener ?
                (SmartOspEventListener) listener : new GenericOspEventAdapter(listener));
        return (smartListener.supportsEventType(eventType));
    }


    protected void sortOspEventListener(List<OspEventListener> ospEventListeners) {
        Collections.sort(ospEventListeners, new Comparator<OspEventListener>() {
            public int compare(OspEventListener o1, OspEventListener o2) {
                if (o1.getOrder() > o2.getOrder()) {
                    return 1;
                } else if (o1.getOrder() < o2.getOrder()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }

    private class ListenerRegistry {

        public List<OspEventListener> ospEventListeners;

        private ListenerRegistry(List<OspEventListener> ospEventListeners) {
            this.ospEventListeners = ospEventListeners;
        }

        public List<OspEventListener> getOspEventListeners() {
            return ospEventListeners;
        }
    }
}

