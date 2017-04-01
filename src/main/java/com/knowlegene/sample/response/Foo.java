/**
 * 版权声明：开源随便使用
 * 日    期：16-5-14
 */
package com.knowlegene.sample.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Admin
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "foo1")
public class Foo {

    @XmlAttribute
    private String field1 = "1";

    @XmlAttribute
    private String field2 = "2";

    public Foo() {
    }

    public Foo(String field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }
}

