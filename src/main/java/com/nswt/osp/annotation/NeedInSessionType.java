/**
 * 版权声明： 开源随便使用
 * 日    期：16-5-31
 */
package com.nswt.osp.annotation;

/**
 * <pre>
 * 功能说明：是否需求会话检查
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public enum NeedInSessionType {
    YES, NO, DEFAULT;

    public static boolean isNeedInSession(NeedInSessionType type) {
        if (YES == type || DEFAULT == type) {
            return true;
        } else {
            return false;
        }
    }
}

