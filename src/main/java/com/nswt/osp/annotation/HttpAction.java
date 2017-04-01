/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-8
 */
package com.nswt.osp.annotation;

/**
 * <pre>
 *   请求类型的方法
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public enum HttpAction {

    GET, POST;

    public static HttpAction fromValue(String value) {
        if (GET.name().equalsIgnoreCase(value)) {
            return GET;
        } else if (POST.name().equalsIgnoreCase(value)) {
            return POST;
        } else {
            return POST;
        }
    }
}

