/**
 * 版权声明： 开源随便使用
 * 日    期：16-5-29
 */
package com.nswt.osp;

/**
 * <pre>
 *   对请求数据进行解析时发生异常
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class OspRequestParseException extends OspException {
    private String requestMessage;

    public OspRequestParseException(String requestMessage) {
        this(requestMessage, "");
    }

    public OspRequestParseException(String requestMessage, String message) {
        this(requestMessage, message, null);
    }

    public OspRequestParseException(String requestMessage, String message, Throwable cause) {
        super(message, cause);
        this.requestMessage = requestMessage;
    }

    public OspRequestParseException(String requestMessage, Throwable cause) {
        this(requestMessage, null, cause);
    }
}

