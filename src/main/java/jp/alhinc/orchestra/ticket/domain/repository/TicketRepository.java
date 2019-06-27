package jp.alhinc.orchestra.ticket.domain.repository;

import jp.alhinc.orchestra.ticket.domain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.finished = :finished")
    long countByFinished(@Param("finished") boolean finished);
}
