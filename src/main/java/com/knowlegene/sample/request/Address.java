/**
 * 版权声明：开源随便使用
 * 日    期：16-4-17
 */
package com.knowlegene.sample.request;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "address")
public class Address {

    @XmlAttribute
    @Pattern(regexp = "\\w{4,30}")
    private String zoneCode;

    @XmlAttribute
    private String doorCode;

    /**
     * 在请求属性的属性类中，你可以使用接口的集合
     */
    @XmlElementWrapper(name = "streets")
    @XmlElement(name = "street")
    private List<Street> streets;

    @XmlElementWrapper(name = "codes")
    private String[] codes;

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getDoorCode() {
        return doorCode;
    }

    public void setDoorCode(String doorCode) {
        this.doorCode = doorCode;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }

    public String[] getCodes() {
        return codes;
    }

    public void setCodes(String[] codes) {
        this.codes = codes;
    }
}

