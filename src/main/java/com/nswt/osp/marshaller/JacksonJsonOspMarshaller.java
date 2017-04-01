/**
 *
 * 日    期：16-2-27
 */
package com.nswt.osp.marshaller;

import com.nswt.osp.OspException;
import com.nswt.osp.OspMarshaller;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

import java.io.IOException;
import java.io.OutputStream;

/**
 * <pre>
 *    将响应对象流化成JSON。 {@link ObjectMapper}是线程安全的。
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class JacksonJsonOspMarshaller implements OspMarshaller {

    private static ObjectMapper objectMapper;

    public void marshaller(Object object, OutputStream outputStream) {
        try {
            JsonGenerator jsonGenerator = getObjectMapper().getJsonFactory().createJsonGenerator(outputStream, JsonEncoding.UTF8);
            getObjectMapper().writeValue(jsonGenerator, object);
        } catch (IOException e) {
            throw new OspException(e);
        }
    }

    private ObjectMapper getObjectMapper() throws IOException {
        if (this.objectMapper == null) {
            ObjectMapper objectMapper = new ObjectMapper();
            AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
            SerializationConfig serializationConfig = objectMapper.getSerializationConfig();
            serializationConfig = serializationConfig.without(SerializationConfig.Feature.WRAP_ROOT_VALUE)
                    .with(SerializationConfig.Feature.INDENT_OUTPUT)
                    .withSerializationInclusion(JsonSerialize.Inclusion.NON_NULL)
                    .withSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY)
                    .withAnnotationIntrospector(introspector);
            objectMapper.setSerializationConfig(serializationConfig);
            this.objectMapper = objectMapper;
        }
        return this.objectMapper;
    }
}

