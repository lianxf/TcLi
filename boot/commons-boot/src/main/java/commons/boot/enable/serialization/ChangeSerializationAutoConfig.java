package commons.boot.enable.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import commons.core.enumeration.TimePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import java.math.BigDecimal;
import java.util.TimeZone;

@Configuration
@ConditionalOnBean(ChangeSerialization.class)
@SuppressWarnings("all")
public class ChangeSerializationAutoConfig {

    @Autowired(required = false)
    ChangeSerialization changeSerialization;

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        TimePattern datePattern = changeSerialization.getDatePattern();
        Boolean serialNull = changeSerialization.getSerialNull();
        TimeZone timeZone = changeSerialization.getTimeZone();

        jackson2ObjectMapperBuilder.timeZone(timeZone);
        jackson2ObjectMapperBuilder.simpleDateFormat(datePattern.getValue());
        jackson2ObjectMapperBuilder.serializerByType(BigDecimal.class,new BigDecimalSerializer());
        jackson2ObjectMapperBuilder.deserializerByType(BigDecimal.class,new BigDecimalDeserialize());
        ObjectMapper mapper = jackson2ObjectMapperBuilder.build();
        mapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS,true);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS,true);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        if (!serialNull){
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        return mapper;
    }
}