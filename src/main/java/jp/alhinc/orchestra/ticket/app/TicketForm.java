package jp.alhinc.orchestra.ticket.app;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class TicketForm implements Serializable {
    private static final long serialVersionUID = 1L;

    // interfaceにstaticは不要
    // →それ実装を伴う = インスタンス生成が必要だから
    public interface TicketCreate {};
    public interface TicketFinish {};
    public interface TicketDelete {};

    // TODO add author
    @NotNull(groups = {TicketFinish.class, TicketDelete.class})
    private Integer id;
    @NotEmpty(groups = {TicketCreate.class})
    private String title;
    @NotEmpty(groups = {TicketCreate.class})
    private String text;
}
