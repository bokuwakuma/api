package jp.alhinc.orchestra.ticket.domain.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Postgresの場合、テーブル定義はSERIAL
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "finished")
    private boolean finished;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
}
