package com.naver.se.test.model;

import java.sql.Timestamp;
import java.util.Map;

public class CollectVO {

    private Timestamp timestamp;
    private String className;
    private String methodName;
    private String parameters;
    private String responseValues;

    public CollectVO(Timestamp timestamp, String className, String methodName, String jsonParams, String jsonRespValues) {
        this.timestamp = timestamp;
        this.className = className;
        this.methodName = methodName;
        this.parameters = jsonParams;
        this.responseValues = jsonRespValues;
    }

    @Override
    public String toString() {
        return "CollectVO{" +
                "timestamp=" + timestamp +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameters='" + parameters + '\'' +
                ", responseValues='" + responseValues + '\'' +
                '}';
    }
}
