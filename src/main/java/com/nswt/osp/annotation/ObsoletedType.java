/**
 * 版权声明： 开源随便使用
 * 日    期：16-7-30
 */
package com.nswt.osp.annotation;

/**
 * <pre>
 *   服务方法是否已经过期，过期的服务方法不能再访问
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public enum  ObsoletedType {
    YES, NO, DEFAULT;

     public static boolean isObsoleted(ObsoletedType type) {
         if (YES == type ) {
             return true;
         } else {
             return false;
         }
     }
}

