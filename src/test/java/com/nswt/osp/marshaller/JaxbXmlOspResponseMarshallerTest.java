/**
 * 版权声明：开源随便使用
 * 日    期：16-5-18
 */
package com.nswt.osp.marshaller;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class JaxbXmlOspResponseMarshallerTest {

    private JaxbXmlOspMarshaller marshaller = new JaxbXmlOspMarshaller();

    @Test
    public void buildMarshaller() throws Throwable {
        //marshaller.marshaller(new SampleResponse(), new ByteArrayOutputStream(1024));
        SampleResponse sampleResponse = new SampleResponse();
        List<HashMap<String, Object>> table = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> row = new HashMap<String, Object>();
        row.put("c1", "c1");
        row.put("c2", "c2");
        row.put("c3", "c3");
        table.add(row);
        sampleResponse.setUserId("json");
        sampleResponse.setTable(table);
        marshaller.marshaller(sampleResponse, System.out);
    }

    @Test
    public void test1(){
        Foo foo = new Foo();
        foo.setB1(true);
        foo.setB2(true);
        foo.setI1(1);
        foo.setI2(1);
        marshaller.marshaller(foo,System.out);
    }
}

