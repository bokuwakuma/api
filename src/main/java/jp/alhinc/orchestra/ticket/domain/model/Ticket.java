package jp.alhinc.orchestra.ticket.domain.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
public class Ticket implements Serializable {
    private String id;
    @NotNull
    @Size(min = 1, max = 60)
    private String title;
    private String text;
    private boolean finished;
    private Date createdAt;
}
