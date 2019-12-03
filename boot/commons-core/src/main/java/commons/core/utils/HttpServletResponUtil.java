package commons.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import commons.core.enumeration.TimePattern;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class HttpServletResponUtil {

    public static void  responJson(HttpServletResponse response,Object object) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.setDateFormat(new SimpleDateFormat(TimePattern.SECOND_PATTERN.getValue()));
        response.getWriter().write(objectMapper.writeValueAsString(object));
    }
}
