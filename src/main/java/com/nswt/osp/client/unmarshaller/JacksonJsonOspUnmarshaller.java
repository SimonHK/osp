/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-30
 */
package com.nswt.osp.client.unmarshaller;

import com.nswt.osp.OspException;
import com.nswt.osp.client.OspUnmarshaller;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

import java.io.IOException;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class JacksonJsonOspUnmarshaller implements OspUnmarshaller {

    private static ObjectMapper objectMapper;


    public <T> T unmarshaller(String content, Class<T> objectType) {
        try {
            return getObjectMapper().readValue(content, objectType);
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
                                                     .withAnnotationIntrospector(introspector);
            objectMapper.setSerializationConfig(serializationConfig);
            this.objectMapper = objectMapper;
        }
        return this.objectMapper;
    }
}

