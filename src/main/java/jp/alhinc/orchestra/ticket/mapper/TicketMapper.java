package jp.alhinc.orchestra.ticket.mapper;

import jp.alhinc.orchestra.ticket.domain.model.Ticket;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.Optional;

@Mapper
public interface TicketMapper {
    Optional<Ticket> findById(String id);
    Collection<Ticket> findAll();
    void create(Ticket ticket);
    boolean updateById(Ticket ticket);
    void deleteById(Ticket ticket);
    long countByFinished(boolean finished);
}
