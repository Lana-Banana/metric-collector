package com.naver.se.test.external.collector;

import com.naver.se.test.model.CollectVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SampleTestExternalCollector {

    private static final Logger logger = LoggerFactory.getLogger(SampleTestExternalCollector.class);

    @Async
    public void sendToCollector(CollectVO collectVO) throws InterruptedException {
        try {
            Thread.sleep(10000);
            logger.info("=========Send collect metric============");
            logger.info(collectVO.toString());
        } catch (InterruptedException e) {
            logger.error(e.toString());
        }

    }
}
