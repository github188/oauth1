package com.yitutech.common.error;

import com.yitutech.common.log.MedicalLog;
import com.yitutech.common.log.MedicalLogFactory;
import java.io.IOException;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * Created by jarontang on 18-3-16.
 */
public class ResourcePatternResolverTest {
    private static MedicalLog log = MedicalLogFactory.getMedicalLog(ResourcePatternResolverTest.class);

    @Test
    public void test() throws IOException {
        ClassLoader cl = this.getClass().getClassLoader();
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver(cl);
        Resource[] resources = patternResolver.getResources("classpath*:/*-code.properties");
        System.out.println("size: " + resources.length);
        for (Resource resource : resources) {
            log.info("resource: " + resource.getFilename());
        }
    }
}
