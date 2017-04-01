/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-30
 */
package com.nswt.osp.client;

/**
 * <pre>
 *   对响应进行反流化
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public interface OspUnmarshaller {

    /**
     * 将字符串反序列化为相应的对象
     *
     * @param inputStream
     * @param objectType
     * @param <T>
     * @return
     */
    <T> T unmarshaller(String content, Class<T> objectType);
}

