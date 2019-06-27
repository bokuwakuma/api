package jp.alhinc.orchestra.api.member;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
public class MemberCredentialResource implements Serializable {

    private static final long serialVersionID = 1L;

    @Size(max = 256)
    @Email
    private String signId;

    // 値がnullの時に、JSONにフィールド自体を出力しないようにするためのアノテーションを指定
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull
    @Size(min = 8, max  = 32)
    private String password;

    @Null
    private Date passwordLastChangedAt;

    @Null
    private Date lastModifiedAt;
}
