/**
 *
 * 日    期：16-2-27
 */
package com.nswt.osp;

import java.io.OutputStream;

/**
 * <pre>
 *   负责将请求方法返回的响应对应流化为相应格式的内容。
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public interface OspMarshaller {
    void marshaller(Object object, OutputStream outputStream);
}

