/**
 * 版权声明： 开源随便使用
 * 日    期：16-5-31
 */
package com.nswt.osp.annotation;

/**
 * <pre>
 *    是否需求进行签名校验.{@link #DEFAULT}是系统预留的，请不要在实际中使用
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public enum IgnoreSignType {

    YES, NO, DEFAULT;

    public static boolean isIgnoreSign(IgnoreSignType type) {
        if (NO == type || DEFAULT == type) {
            return false;
        } else {
            return true;
        }
    }
}

