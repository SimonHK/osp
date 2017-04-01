/**
 *
 * 日    期：16-2-22
 */
package com.nswt.osp.response;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <pre>
 *    通用的响应对象
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "response")
public class CommonOspResponse {

    @XmlAttribute
    private boolean successful = false;

    public static final CommonOspResponse SUCCESSFUL_RESPONSE = new CommonOspResponse(true);
    public static final CommonOspResponse FAILURE_RESPONSE = new CommonOspResponse(false);

    public CommonOspResponse() {
    }

    private CommonOspResponse(boolean successful) {
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }
}

