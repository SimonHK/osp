/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-30
 */
package com.nswt.osp.client;

import com.nswt.osp.response.ErrorResponse;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class DefaultCompositeResponse<T> implements CompositeResponse {

    private boolean successful;

    private ErrorResponse errorResponse;

    private T successOspResponse;

    public DefaultCompositeResponse(boolean successful) {
        this.successful = successful;
    }


    public ErrorResponse getErrorResponse() {
        return this.errorResponse;
    }


    public T getSuccessResponse() {
        return this.successOspResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public void setSuccessRopResponse(T successOspResponse) {
        this.successOspResponse = successOspResponse;
    }


    public boolean isSuccessful() {
        return successful;
    }
}

