/**
 * 版权声明：开源随便使用
 * 日    期：16-7-17
 */
package com.knowlegene.sample;

import com.nswt.osp.session.Session;
import com.nswt.osp.session.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class SampleSessionManager implements SessionManager{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<String, Session> sessionCache = new ConcurrentHashMap<String, Session>(128, 0.75f, 32);


    public void addSession(String sessionId, Session session) {
        sessionCache.put(sessionId, session);
    }


    public Session getSession(String sessionId) {
        return sessionCache.get(sessionId);
    }


    public void removeSession(String sessionId) {
        sessionCache.remove(sessionId);
    }
}

