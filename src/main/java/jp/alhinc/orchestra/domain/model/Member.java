package jp.alhinc.orchestra.domain.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    private String memberId;
    private String firstName;
    private String lastName;
    private String genderCode;
    private LocalDate dateOfBirth;
    private String emailAddress;
    private String phoneNumber;
    private String zipCode;
    private String address;
    private Date createdAt;
    private Date lastModifiedAt;
    private long version;
    private MemberCredential credential;

}
