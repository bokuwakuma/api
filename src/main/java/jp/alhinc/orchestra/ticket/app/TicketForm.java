package jp.alhinc.orchestra.ticket.app;

import lombok.Data;

import java.io.Serializable;

@Data
public class TicketForm implements Serializable {
    // TODO add author
    private String title;
    private String text;
}
