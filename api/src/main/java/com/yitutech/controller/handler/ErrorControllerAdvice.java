package com.yitutech.controller.handler;

import static com.yitutech.common.result.ResultFactory.err;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.yitutech.common.error.CommonCode;
import com.yitutech.common.log.MedicalLog;
import com.yitutech.common.log.MedicalLogFactory;
import com.yitutech.common.result.Result;
import java.util.Enumeration;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author jarontang
 * @date 18-3-16
 */
@ControllerAdvice
public class ErrorControllerAdvice {

    private static MedicalLog log = MedicalLogFactory.getMedicalLog(ErrorControllerAdvice.class);

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    private void init() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Result requestHandlingNoHandlerFound(HttpServletRequest request, Exception e) {
        log(CommonCode.NOT_FOUND, request, e);
        return err(CommonCode.NOT_FOUND);
    }

    @ExceptionHandler({
        MissingServletRequestParameterException.class, ServletRequestBindingException.class,
        TypeMismatchException.class, HttpMessageNotReadableException.class,
        MethodArgumentNotValidException.class, MissingServletRequestPartException.class,
        BindException.class
    })
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Result requestHandlingBadRequests(HttpServletRequest request, Exception e) {
        log(CommonCode.BAD_REQUEST, request, e);
        return err(CommonCode.BAD_REQUEST);
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Result requestHandlingNotAvailable(HttpServletRequest request, Exception e) {
        log(CommonCode.SERVICE_UNAVAILABLE, request, e);
        return err(CommonCode.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Result requestHandlingMethodNotSupported(HttpServletRequest request, Exception e) {
        log(CommonCode.METHOD_NOT_ALLOWED, request, e);
        return err(CommonCode.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Result requestHandlingHttpMediaTypeNotSupported(HttpServletRequest request,
        Exception e) {
        log(CommonCode.UNSUPPORTED_MEDIA_TYPE, request, e);
        return err(CommonCode.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Result requestHandlingNumberFormatException(HttpServletRequest request, Exception e) {
        log(CommonCode.BAD_REQUEST, request, e);
        return err(CommonCode.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Result requestHandlingHttpMediaTypeNotAcceptable(HttpServletRequest request,
        Exception e) {
        log(CommonCode.NOT_ACCEPTABLE, request, e);
        return err(CommonCode.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({
        MissingPathVariableException.class, ConversionNotSupportedException.class,
        HttpMessageNotWritableException.class})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Result requestHandlingInternalError(HttpServletRequest request, Exception e) {
        log(CommonCode.INTERNAL_SERVER_ERROR, request, e);
        return err(CommonCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
        MySQLIntegrityConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Result requestHandlingDatabaseError(HttpServletRequest request, Exception e) {
        log(CommonCode.DB_CONSTRAINT_VIOLATION, request, e);
        return err(CommonCode.DB_CONSTRAINT_VIOLATION);
    }

    private void log(int code, HttpServletRequest request, Exception e) {
        try {
            Builder<String, Object> headerBuilder = ImmutableMap.builder();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                headerBuilder.put(key, request.getHeader(key));
            }
            ImmutableMap<String, Object> headers = headerBuilder.build();
            ImmutableMap<String, Object> req = ImmutableMap.<String, Object>builder()
                .put("uri", request.getRequestURI())
                .put("header", headers)
                .put("host", request.getRemoteAddr())
                .put("cookies", request.getCookies())
                .build();
            log.error(code, "\n" + objectMapper.writeValueAsString(req), e);
        } catch (JsonProcessingException e1) {
            log.error("fail to jsonify HttpServletRequest", e1);
        }
    }
}
