package jp.alhinc.orchestra.api.member;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
public class MemberResource implements Serializable {
    private static final long serialVersionUID = 1L;

    interface PostMembers {
    }

    interface PutMember{}

    @Null(groups = PostMembers.class)
    @NotEmpty(groups = PutMember.class)
    @Size(min = 10, max = 10, groups = PutMember.class)
    private String memberId;

    @NotEmpty
    @Size(max = 128)
    private String firstName;

    @NotEmpty
    @Size(max = 128)
    private String lastName;

    @NotEmpty
    private String genderCode;

    @NotNull
    @Past
    private LocalDate dateOfBirth;

    @NotEmpty
    @Size(max = 256)
    @Email
    private String emaliAddress;

    @Size(max = 20)
    private String phoneNumber;

    @Size(max = 20)
    private String zipCode;

    @Size(max = 20)
    private String address;

    @NotNull(groups = PostMembers.class)
    @Null(groups = PutMember.class)
    private MemberCredentialResource credential;

    @Null
    private Date createdAt;

    @Null
    private Date lastModifiedAt;



}
