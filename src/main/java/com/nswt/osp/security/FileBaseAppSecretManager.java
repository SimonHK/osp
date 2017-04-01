/**
 * 版权声明： 开源随便使用
 * 日    期：16-3-1
 */
package com.nswt.osp.security;

import com.nswt.osp.OspException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * <pre>
 *   基于文件管理的应用密钥
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class FileBaseAppSecretManager implements AppSecretManager {

    private static final String OSP_APP_SECRET_PROPERTIES = "osp.appSecret.properties";

    private String appSecretFile = OSP_APP_SECRET_PROPERTIES;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private Properties properties;

    public String getSecret(String appKey) {
        if (properties == null) {
            try {
                DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
                Resource resource = resourceLoader.getResource(appSecretFile);
                properties =   PropertiesLoaderUtils.loadProperties(resource);
            } catch (IOException e) {
                throw new OspException("在类路径下找不到osp.appSecret.properties的应用密钥的属性文件", e);
            }
        }
        String secret = properties.getProperty(appKey);

        if (secret == null) {
            logger.error("不存在应用键为{0}的密钥,请检查应用密钥的配置文件。", appKey);
        }
        return secret;
    }

    public void setAppSecretFile(String appSecretFile) {
        this.appSecretFile = appSecretFile;
    }


    public boolean isValidAppKey(String appKey) {
        return getSecret(appKey) != null;
    }
}

