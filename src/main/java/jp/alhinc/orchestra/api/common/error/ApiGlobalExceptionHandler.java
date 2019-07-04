package jp.alhinc.orchestra.api.common.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    ApiErrorCreator apiErrorCreator;

//    @Autowired
//    ExceptionCodeResolver exceptionCodeResolver;

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatus status,
                                                             WebRequest request) {
        final Object apiError;
//        if (body == null) {
//            // エラーコードと例外のマッピング
//            String errorCode = exceptionCodeResolver.resolverExceptionCode(e);
//            apiError = apiErrorCreator.createApiError(request, errorCode, e.getLocalizedMessage());
//        } else {
//            apiError = body;
//        }

        apiError = body;

        return ResponseEntity.status(status).headers(headers).body(apiError);
    }
}
