package commons.boot.enable.exception;

import com.google.common.collect.Lists;
import commons.core.exception.BusinessException;
import commons.core.response.Response;
import commons.core.utils.RespUtil;
import commons.core.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
@ConditionalOnBean(EnableExceptionHandler.class)
public class ExceptionHandlerAutoConfig {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Response businessException(BusinessException e){
        StackTraceElement stackTrace = e.getStackTrace()[0];
        String errorMsg = e.getMessage();
        String msg = "类" + stackTrace.getClassName() + "的第" + stackTrace.getLineNumber() + "行的" + stackTrace.getMethodName() + "方法出现:"+errorMsg;
        log.error(msg);
        return RespUtil.error(e.getCode(),errorMsg);
    }

    /**   缺失参数   状态码:400   */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Response handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        String message = e.getMessage();
        String parameter = StringUtil.subBetween(message, "parameter ", " is not present");
        String errorMsg = "URL后面缺少必要参数：url?"+parameter+"=值";
        log.error(errorMsg);
        return RespUtil.error(400,errorMsg);
    }

    /**   参数解析失败   状态码:400   */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String message = e.getMessage();
        String unKnow = StringUtil.subBetween(message, "through reference chain: ", ")");
        String knowKeys = StringUtil.subBetween(message,"3 known properties: ", "])\n at [Source:");
        String unKnowKeys = StringUtil.subBetween(unKnow, "[", "]");
        String className = StringUtil.removeSuffix(unKnow,"["+unKnowKeys+"]");
        log.error("类: "+className+" 只有 "+knowKeys+" 属性，没有 "+unKnowKeys+" 属性");
        return RespUtil.error(400,"没有 "+unKnowKeys+" 这样的Key键，只有 "+knowKeys+" 这些键");
    }


    /**  不支持当前请求方法  状态码:401    */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String method = e.getMethod();
        ArrayList<String> strings = Lists.newArrayList(e.getSupportedMethods());
        String errorMsg = "当前方法只支持: "+strings+" 请求方式,不支持: " +method +"请求方式";
        log.error(errorMsg);
        return RespUtil.error(401,errorMsg);
    }

    /**   不支持当前媒体类型  状态码:402   */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public Response handleHttpMediaTypeNotSupportedException(Exception e) {
        StackTraceElement stackTrace = e.getStackTrace()[0];
        String errorMsg = "不支持当前媒体类型";
        String msg = "类" + stackTrace.getClassName() + "的第" + stackTrace.getLineNumber() + "行的" + stackTrace.getMethodName() + "方法出现:"+errorMsg;
        log.error(msg);
        return  RespUtil.error(402,errorMsg);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Response handleNullPointerException(NullPointerException e) {
        StackTraceElement stackTrace = e.getStackTrace()[0];
        String errorMsg = "空指针异常";
        String msg = "类" + stackTrace.getClassName() + "的第" + stackTrace.getLineNumber() + "行的" + stackTrace.getMethodName() + "方法出现:"+errorMsg;
        log.error(msg);
        return  RespUtil.error(504,errorMsg);
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Response handleArithmeticException(ArithmeticException e) {
        StackTraceElement stackTrace = e.getStackTrace()[0];
        String errorMsg = "0不能做被除数";
        String msg = "类" + stackTrace.getClassName() + "的第" + stackTrace.getLineNumber() + "行的" + stackTrace.getMethodName() + "方法出现:"+errorMsg;
        log.error(msg);
        return  RespUtil.error(505,errorMsg);
    }


    /**   校验失败  状态码:403   */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response handleConstraintViolationException(MethodArgumentNotValidException e) {
        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();
        String s = objectErrors.get(0).toString();
        String className = StringUtil.toBigCamelCase(StringUtil.subBetween(s, "Field error in object '", "' on field "));
        String filed = StringUtil.subBetween(s,"on field ",": rejected value");
        String errorMsg = StringUtil.subBetween(StringUtil.subAfter(s, ";", true),"default message [","]");
        String msg = className+"类的"+filed+"属性:"+errorMsg;
        log.error(msg);
        return RespUtil.error(403,errorMsg);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response handleException(Exception e) {
        StackTraceElement stackTrace = e.getStackTrace()[0];
        String errorMsg = e.getMessage() == null ? "未知异常":e.getMessage();
        String msg = "类" + stackTrace.getClassName() + "的第" + stackTrace.getLineNumber() + "行的" + stackTrace.getMethodName() + "方法出现:"+errorMsg;
        log.error(msg);
        return  RespUtil.error(500,errorMsg);
    }
}
