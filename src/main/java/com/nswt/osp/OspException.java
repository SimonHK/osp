/**
 *
 * 日    期：16-2-13
 */
package com.nswt.osp;

/**
 * <pre>
 *   OSP的异常。
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class OspException extends RuntimeException {
    public OspException() {
    }

    public OspException(String message) {
        super(message);
    }

    public OspException(String message, Throwable cause) {
        super(message, cause);
    }

    public OspException(Throwable cause) {
        super(cause);
    }
}

