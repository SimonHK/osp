/**
 *
 * 日    期：16-2-27
 */
package com.nswt.osp.marshaller;

import com.nswt.osp.OspException;
import com.nswt.osp.OspMarshaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 *    将对象流化成XML，每个类型对应一个{@link JAXBContext}，{@link JAXBContext} 是线程安全的，但是
 * {@link Marshaller}是非线程安全的，因此需要每次创建一个。
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class JaxbXmlOspMarshaller implements OspMarshaller {

    private static Map<Class, JAXBContext> jaxbContextHashMap = new ConcurrentHashMap<Class, JAXBContext>();

    public void marshaller(Object object, OutputStream outputStream) {
        try {
            Marshaller m = buildMarshaller(object.getClass());
            m.marshal(object, outputStream);
        } catch (JAXBException e) {
            throw new OspException(e);
        }
    }


    private Marshaller buildMarshaller(Class<?> objectType) throws JAXBException {
        if (!jaxbContextHashMap.containsKey(objectType)) {
            JAXBContext context = JAXBContext.newInstance(objectType);
            jaxbContextHashMap.put(objectType, context);
        }
        JAXBContext context = jaxbContextHashMap.get(objectType);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
        return marshaller;
    }
}
