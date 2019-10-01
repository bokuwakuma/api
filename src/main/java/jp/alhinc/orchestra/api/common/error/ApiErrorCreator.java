package jp.alhinc.orchestra.api.common.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;

@Component
public class ApiErrorCreator {

    @Autowired
    MessageSource messageSource;

    public ApiError createApiError(WebRequest request, String errorCode,
                                   String defaultErrorMessage, Object... args) {
        String localizedMessage = messageSource.getMessage(errorCode, args, defaultErrorMessage, request.getLocale());
        return new ApiError(errorCode, localizedMessage);
    }

    public ApiError createBindingResultApiError(WebRequest request,
                                                String errorCode, BindingResult bindingResult,
                                                String defaultErrorMessage) {
        ApiError apiError = createApiError(request, errorCode, defaultErrorMessage);
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            apiError.addDetail(createApiError(request, errorCode, defaultErrorMessage, fieldError.getField()));
        }
        return apiError;
    }
}
