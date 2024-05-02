package org.iwms.common.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author leung
 */
public class ResponseMsgUtil {

    static final Logger logger = LoggerFactory.getLogger(ResponseMsgUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String writeObjectAsJsonString(Object o){
        try {
            return objectMapper.writeValueAsString(o);
        }catch (JsonProcessingException e){
            logger.error(e.toString());
            return null;
        }
    }
}
