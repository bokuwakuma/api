package jp.alhinc.orchestra.ticket.domain.service;



import jp.alhinc.orchestra.ticket.domain.model.Ticket;

import java.util.Collection;

public interface TicketService {
    Collection<Ticket> findAll();
    Ticket findOne(Integer id);
    Ticket create(Ticket ticket);
    Ticket finish(Integer id);
    void delete(Integer id);
}