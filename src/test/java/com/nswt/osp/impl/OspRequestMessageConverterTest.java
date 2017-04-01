/**
 * 版权声明：开源随便使用
 * 日    期：16-5-29
 */
package com.nswt.osp.impl;

import com.nswt.osp.MessageFormat;
import com.nswt.osp.OspContext;
import com.nswt.osp.request.OspRequestMessageConverter;
import org.springframework.core.convert.TypeDescriptor;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;


/**
 * @author Admin
 * @version 1.0
 */
public class OspRequestMessageConverterTest {

    @Test
    public void testConvertOfXmlFormat() throws Exception {
        OspContext ospContext = mock(OspContext.class);
        SimpleOspRequestContext context = new SimpleOspRequestContext(ospContext);
        context.setMessageFormat(MessageFormat.xml);

        TypeDescriptor addrTypeDescriptor = TypeDescriptor.valueOf(Addresss.class);
        TypeDescriptor strTypeDescriptor = TypeDescriptor.valueOf(String.class);
        OspRequestMessageConverter converter = new OspRequestMessageConverter();
        String addressStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<address zoneCode=\"001\" doorCode=\"002\">\n" +
                "  <streets>\n" +
                "    <street no=\"001\" name=\"street1\"/>\n" +
                "    <street no=\"002\" name=\"street2\"/>\n" +
                "  </streets>\n" +
                "</address>";
        Object destObj = converter.convert(addressStr, strTypeDescriptor, addrTypeDescriptor);
        assertTrue(destObj instanceof Addresss);
        Addresss addresss = (Addresss) destObj;
        assertEquals(addresss.getZoneCode(), "001");
        assertEquals(addresss.getDoorCode(), "002");
        assertEquals(addresss.getStreets().size(), 2);
    }

    @Test
    public void testConvertOfJsonFormat() throws Exception {
        OspContext ospContext = mock(OspContext.class);
        SimpleOspRequestContext context = new SimpleOspRequestContext(ospContext);
        context.setMessageFormat(MessageFormat.json);

        TypeDescriptor addrTypeDescriptor = TypeDescriptor.valueOf(Addresss.class);
        TypeDescriptor strTypeDescriptor = TypeDescriptor.valueOf(String.class);
        OspRequestMessageConverter converter = new OspRequestMessageConverter();
        String addressStr = "{\"zoneCode\":\"001\",\"doorCode\":\"002\",\"streets\":[{\"no\":\"001\",\"name\":\"street1\"}]}";
        Object destObj = converter.convert(addressStr, strTypeDescriptor, addrTypeDescriptor);
        assertTrue(destObj instanceof Addresss);
        Addresss addresss = (Addresss) destObj;
        assertEquals(addresss.getZoneCode(), "001");
        assertEquals(addresss.getDoorCode(), "002");
        assertNotNull(addresss.getStreets());
        assertEquals(addresss.getStreets().size(), 1);
    }
}

