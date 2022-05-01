package com.naver.se.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naver.se.test.external.collector.SampleTestExternalCollector;
import com.naver.se.test.model.CollectVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

@Service
public class CollectService {

    private static final Logger logger = LoggerFactory.getLogger(CollectService.class);
    @Autowired
    private SampleTestExternalCollector sampleTestExternalCollector;

    @Async
    public void sendCollection(Timestamp timestamp, String className, String methodName, Map<String, String> params, Map<String, String> respValues) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonParams = objectMapper.writeValueAsString(params);
            String jsonRespValues = objectMapper.writeValueAsString(respValues);
            CollectVO collectVO = new CollectVO(timestamp, className, methodName, jsonParams, jsonRespValues);

            sampleTestExternalCollector.sendToCollector(collectVO);

        } catch (Exception e) {
            logger.error(e.toString());
        }

    }
}
