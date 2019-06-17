package jp.alhinc.orchestra.ticket.domain.repository;

import jp.alhinc.orchestra.ticket.domain.model.Ticket;

import java.util.Collection;
import java.util.Optional;

public interface TicketRepository {
    Optional<Ticket> findById(String id);
    Collection<Ticket> findAll();
    void create(Ticket ticket);
    boolean updateById(Ticket ticket);
    void deleteById(Ticket ticket);
    long countByFinished(boolean finished);
}
