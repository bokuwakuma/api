package jp.alhinc.orchestra.ticket.app;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class TicketForm implements Serializable {
    private static final long serialVersionUID = 1L;

    public static interface TicketCreate {};
    public static interface TicketFinish {};
    public static interface TicketDelete {};

    // TODO add author
    @NotEmpty(groups = {TicketFinish.class, TicketDelete.class})
    private String id;
    @NotEmpty(groups = {TicketCreate.class})
    private String title;
    @NotEmpty(groups = {TicketCreate.class})
    private String text;
}
