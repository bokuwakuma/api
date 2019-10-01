package jp.alhinc.orchestra.api.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class MemberSearchQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String name;
}
