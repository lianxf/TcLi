package commons.core.utils;


import commons.core.exception.BusinessException;
import commons.core.response.Response;

@SuppressWarnings({"all"})
public class RespUtil {

    /**
     * 返回成功，传入返回体具体出參
     *
     * @param object
     * @return Response
     */
    public static<T> Response<T> success(T object) {
        Response<T> result = new Response();
        result.setData(object);
        return result;
    }

    /**
     * 提供给部分不需要入参的接口
     *
     * @return Response
     */
    public static Response success() {
        return success("SUCCESS");
    }

    public static Response error(Integer code, String msg) {
        Response result = new Response();
        result.setCode(code);
        result.setMsg(msg);
        result.setData("ERROR");
        return result;
    }

    public static Response error(BusinessException exceptionEnum) {
        Response result = new Response();
        result.setCode(exceptionEnum.getCode());
        result.setMsg(exceptionEnum.getMessage());
        result.setData("ERROR");
        return result;
    }

    public static Response error(Integer code, String msg, Object object) {
        Response result = new Response();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }
}