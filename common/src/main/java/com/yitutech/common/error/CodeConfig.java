package com.yitutech.common.error;

import com.yitutech.common.log.MedicalLog;
import com.yitutech.common.log.MedicalLogFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * Created by Easy
 */
public class CodeConfig {

    private static MedicalLog logger = MedicalLogFactory.getMedicalLog(CodeConfig.class);

    private static volatile CodeConfig codeConfig;
    private Properties properties = new Properties();

    private CodeConfig() {
        init();
    }

    public static CodeConfig getCodeConfig() {
        if (null == codeConfig) {
            synchronized (CodeConfig.class) {
                if (null == codeConfig) {
                    codeConfig = new CodeConfig();
                }
            }
        }

        return codeConfig;
    }


    /**
     * error_code.properties初始化
     */
    private void init() {
        logger.info("[init] start init error code properties ... ...");
        reload();
    }

    public void reload() {

        logger.info("start loading code properties ... ...");
        ClassLoader cl = this.getClass().getClassLoader();
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver(cl);
        try {
            Resource[] resources = patternResolver.getResources("classpath*:/*-code.properties");
            for (Resource resource : resources) {
                logger.info("try to load properties file: " + resource.getFilename());
                Properties prop = getProperties(resource.getInputStream());
                logger.info("succeed to load properties file: " + resource.getFilename());
                properties.putAll(prop);
            }
        } catch (IOException e) {
            logger.error("Fail to read properties file", e);
        }
    }

    private Properties getProperties(InputStream resourceAsStream) {
        Properties properties = new Properties();
        try (InputStreamReader inputStream = new InputStreamReader(resourceAsStream, "UTF-8")) {
            properties.load(inputStream);
        } catch (UnsupportedEncodingException e) {
            logger.error(CommonCode.SYS_UNSUPPORTED_ENCODING_ERROR, "不支持的编码。", e);
        } catch (IOException e) {
            logger.error(CommonCode.SYS_IO_ERROR, "加载错误码属性配置文件异常。", e);
        }
        return properties;
    }

    public String getMessage(int code) {
        return getMessage(String.valueOf(code));
    }

    public String getMessage(int code, String defaultMessage) {
        return getMessage(String.valueOf(code), defaultMessage);
    }

    public String getMessage(String code) {
        return properties.getProperty(code);
    }

    public String getMessage(String code, String defaultMessage) {
        return properties.getProperty(code, defaultMessage);
    }

}
