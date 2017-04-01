/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-5
 */
package com.nswt.osp.response;

import com.nswt.osp.security.MainError;
import com.nswt.osp.security.SubError;
import com.nswt.osp.security.SubErrorType;
import com.nswt.osp.security.SubErrors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Locale;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "error")
public class TimeoutErrorResponse extends ErrorResponse {

    private static final String ISP = "isp.";

    private static final String SERVICE_TIMEOUT = "-service-timeout";

    public TimeoutErrorResponse() {
    }

    public TimeoutErrorResponse(String method, Locale locale, int timeout) {
        MainError mainError = SubErrors.getMainError(SubErrorType.ISP_SERVICE_TIMEOUT, locale);

        ArrayList<SubError> subErrors = new ArrayList<SubError>();

        String errorCodeKey = ISP + transform(method) + SERVICE_TIMEOUT;
        SubError subError = SubErrors.getSubError(errorCodeKey,
                SubErrorType.ISP_SERVICE_TIMEOUT.value(),
                locale,
                method, timeout);
        subErrors.add(subError);

        setSubErrors(subErrors);
        setMainError(mainError);
    }

}

