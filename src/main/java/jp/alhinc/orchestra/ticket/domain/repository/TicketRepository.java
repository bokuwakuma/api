package jp.alhinc.orchestra.ticket.domain.repository;

import jp.alhinc.orchestra.ticket.domain.model.Ticket;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, String> {

    Optional<Ticket> findById(String id);

    @Override
    List<Ticket> findAll();

    void create(Ticket ticket);
    boolean updateById(Ticket ticket);
    void deleteById(Ticket ticket);
    long countByFinished(boolean finished);
}
