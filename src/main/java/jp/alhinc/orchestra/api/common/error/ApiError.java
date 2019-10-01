package jp.alhinc.orchestra.api.common.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * エラー情報を保持するJavaBean
 * Setterは不要
 */
@Getter
public class ApiError implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String code;
    private final String message;

    /**
     * エラー対象の項目名
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final String target;

    /**
     * エラー詳細情報
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<ApiError> details = new ArrayList<>();

    public ApiError(String code, String message) {
        this(code, message, null);
    }

    public ApiError(String code, String message, String target) {
        this.code = code;
        this.message = message;
        this.target = target;
    }

    public void addDetail(ApiError apiError) {
        this.details.add(apiError);
    }
}
