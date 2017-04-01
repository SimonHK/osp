
package com.knowlegene.sample.sys;

import com.knowlegene.sample.converter.DateUtils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;

/**
 * @author : Admin
 * @date: 14-3-18
 */
public class DateXmlAdapter extends XmlAdapter<String,Date> {


    public Date unmarshal(String v) throws Exception {
        return DateUtils.parseDate(v);
    }


    public String marshal(Date date) throws Exception {
        return DateUtils.format(date,DateUtils.DATETIME_FORMAT);
    }
}
