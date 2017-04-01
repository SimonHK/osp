/**
 * 版权声明： 开源随便使用
 * 日    期：16-8-3
 */
package com.nswt.osp.request;

import org.springframework.core.convert.converter.Converter;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public interface OspConverter<S, T> extends Converter<S, T> {

    /**
     * 从T转换成S
     * @param target
     * @return
     */
    S unconvert(T target);

    /**
     * 获取源类型
     * @return
     */
    Class<S> getSourceClass();

    /**
     * 获取目标类型
     * @return
     */
    Class<T> getTargetClass();
}

