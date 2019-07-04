package jp.alhinc.orchestra.domain.model;

import lombok.Data;

import java.util.Date;

@Data
public class MemberCredential {
    private final long serialVersionUID = 1L;

    private String memberId;
    private String signId;
    private String password;
    private String previousPassword;
    private Date passwordLastChangeAt;
    private Date lastModifiedAt;
}
