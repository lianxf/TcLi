package commons.boot.objectmapper;

import cn.likepeng.commons.core.enumeration.TimePattern;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
@ConditionalOnBean(EnableObjectMapper.class)
public class ObjectMapperConfig {

    @Autowired(required = false)
    EnableObjectMapper enableObjectMapper;

    @Bean
    public ObjectMapper objectMapper() {
        TimePattern datePattern = enableObjectMapper.getDatePattern();
        Boolean isSerialNull = enableObjectMapper.getIsSerialNull();
        Boolean numberToString = enableObjectMapper.getNumberToString();
        TimeZone timeZone = enableObjectMapper.getTimeZone();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN,numberToString);
        mapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS,numberToString);

        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        if (!isSerialNull){
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }

        mapper.setDateFormat(new SimpleDateFormat(datePattern.getValue()));
        mapper.setTimeZone(timeZone);
        return mapper;
    }
}