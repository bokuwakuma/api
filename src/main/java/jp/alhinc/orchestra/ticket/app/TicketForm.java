package jp.alhinc.orchestra.ticket.app;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class TicketForm implements Serializable {
    private static final long serialVersionUID = 1L;

    // TODO add author
    @NotNull
    private String title;
    @NotNull
    private String text;
}
