/**
 * 版权声明： 开源随便使用
 * 日    期：16-4-17
 */
package com.nswt.osp.request;

import com.nswt.osp.MessageFormat;
import com.nswt.osp.OspException;
import com.nswt.osp.OspRequestParseException;
import com.nswt.osp.impl.SimpleOspRequestContext;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.util.Assert;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.StringReader;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <pre>
 *     将参数中的XML或JSON转换为对象的属性对象
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class OspRequestMessageConverter implements ConditionalGenericConverter {

    private static final ConcurrentMap<Class, JAXBContext> jaxbContexts = new ConcurrentHashMap<Class, JAXBContext>();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
        SerializationConfig serializationConfig = objectMapper.getSerializationConfig();
        serializationConfig = serializationConfig.without(SerializationConfig.Feature.WRAP_ROOT_VALUE)
                                                 .withAnnotationIntrospector(introspector);
        objectMapper.setSerializationConfig(serializationConfig);
    }


    /**
     * 如果目标属性类有标注JAXB的注解，则使用该转换器
     *
     * @param sourceType
     * @param targetType
     * @return
     */
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        Class clazz = targetType.getObjectType();
        return clazz.isAnnotationPresent(XmlRootElement.class) || clazz.isAnnotationPresent(XmlType.class);
    }

    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, Object.class));
    }

    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        try {
            if (SimpleOspRequestContext.messageFormat.get() == MessageFormat.json) {//输入格式为JSON
                JsonParser jsonParser = objectMapper.getJsonFactory().createJsonParser((String) source);
                return jsonParser.readValueAs(targetType.getObjectType());
            } else {
                Unmarshaller unmarshaller = createUnmarshaller(targetType.getObjectType());
                StringReader reader = new StringReader((String) source);
                return unmarshaller.unmarshal(reader);
            }
        } catch (Exception e) {
            throw new OspRequestParseException((String) source, e);
        }
    }

    private Unmarshaller createUnmarshaller(Class clazz) throws JAXBException {
        try {
            JAXBContext jaxbContext = getJaxbContext(clazz);
            return jaxbContext.createUnmarshaller();
        } catch (JAXBException ex) {
            throw new OspException(
                    "Could not create Unmarshaller for class [" + clazz + "]: " + ex.getMessage(), ex);
        }
    }

    private JAXBContext getJaxbContext(Class clazz) {
        Assert.notNull(clazz, "'clazz' must not be null");
        JAXBContext jaxbContext = jaxbContexts.get(clazz);
        if (jaxbContext == null) {
            try {
                jaxbContext = JAXBContext.newInstance(clazz);
                jaxbContexts.putIfAbsent(clazz, jaxbContext);
            } catch (JAXBException ex) {
                throw new HttpMessageConversionException(
                        "Could not instantiate JAXBContext for class [" + clazz + "]: " + ex.getMessage(), ex);
            }
        }
        return jaxbContext;
    }


}

