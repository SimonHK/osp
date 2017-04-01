/**
 * 版权声明： 开源随便使用
 * 日    期：16-3-1
 */
package com.nswt.osp.security;

/**
 * <pre>
 *    应用键管理器，可根据appKey获取对应的secret.
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public interface AppSecretManager {

    /**
     * 获取应用程序的密钥
     *
     * @param appKey
     * @return
     */
    String getSecret(String appKey);

    /**
     * 是否是合法的appKey
     *
     * @param appKey
     * @return
     */
    boolean isValidAppKey(String appKey);
}

