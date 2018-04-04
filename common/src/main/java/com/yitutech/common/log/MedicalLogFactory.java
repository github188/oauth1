package com.yitutech.common.log;

import org.slf4j.LoggerFactory;

/**
 * Created by Easy
 */
public class MedicalLogFactory {

    private MedicalLogFactory () {
    }

    public static MedicalLog getMedicalLog(Class<?> classz) {
        return new MedicalLog(LoggerFactory.getLogger(classz));
    }

    public static MedicalLog getMedicalLog(String name) {
        return new MedicalLog(LoggerFactory.getLogger(name));
    }
}
