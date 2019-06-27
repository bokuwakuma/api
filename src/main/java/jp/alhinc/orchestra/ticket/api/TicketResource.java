package jp.alhinc.orchestra.ticket.api;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
public class TicketResource implements Serializable {
    private static final long serialVersionID = 1L;

    private Integer id;
    @NotNull
    @Size(min = 1, max = 60)
    private String title;
    private String text;
    private boolean finished;
    private Date createdAt;
}
