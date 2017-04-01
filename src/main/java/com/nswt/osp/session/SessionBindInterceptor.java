/**
 * Copyright： 版权所有 违者必究 2013
 */
package com.nswt.osp.session;

import com.nswt.osp.CommonConstant;
import com.nswt.osp.OspRequestContext;
import com.nswt.osp.AbstractInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 将{@link Session}绑定到{@link OspSessionHolder}中，默认注册。
 *
 * @author : Admin
 * @date: 16-10-16
 */
public class SessionBindInterceptor extends AbstractInterceptor {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    public void beforeService(OspRequestContext ospRequestContext) {
        Session session = ospRequestContext.getSession();
        if (session != null) {
            OspSessionHolder.put(session);
            if (logger.isDebugEnabled()) {
                logger.debug("会话绑定到{}中", OspSessionHolder.class.getCanonicalName());
            }
        }
    }


    public void beforeResponse(OspRequestContext ospRequestContext) {
        Session session = ospRequestContext.getSession();
        if (session != null && session.isChanged()) {
            session.removeAttribute(CommonConstant.SESSION_CHANGED);
            SessionManager sessionManager = ospRequestContext.getOspContext().getSessionManager();
            sessionManager.addSession(ospRequestContext.getSessionId(), session);
            if (logger.isDebugEnabled()) {
                logger.debug("会话内容发生更改，将其同步到外部缓存管理器中。");
            }
        }
    }
}
