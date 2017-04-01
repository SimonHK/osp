/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-8
 */
package com.knowlegene.sample.converter;

import com.nswt.osp.request.OspConverter;
import com.knowlegene.sample.request.Telephone;
import org.springframework.util.StringUtils;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class TelephoneConverter implements OspConverter<String, Telephone> {


    public Telephone convert(String source) {
        if (StringUtils.hasText(source)) {
            String zoneCode = source.substring(0, source.indexOf("-"));
            String telephoneCode = source.substring(source.indexOf("-") + 1);
            Telephone telephone = new Telephone();
            telephone.setZoneCode(zoneCode);
            telephone.setTelephoneCode(telephoneCode);
            return telephone;
        } else {
            return null;
        }
    }


    public String unconvert(Telephone target) {
        StringBuilder sb = new StringBuilder();
        sb.append(target.getZoneCode());
        sb.append("-");
        sb.append(target.getTelephoneCode());
        return sb.toString();
    }


    public Class<String> getSourceClass() {
        return String.class;
    }


    public Class<Telephone> getTargetClass() {
        return Telephone.class;
    }
}

