package jp.alhinc.orchestra.ticket.domain.repository;

import jp.alhinc.orchestra.ticket.domain.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TicketRepositoryImpl implements TicketRepository {

    // TODO refactor DB access
    private static final Map<String, Ticket> TICKET_MAP = new ConcurrentHashMap<String, Ticket>();

    @Override
    public Optional<Ticket> findById(String id) {
        return Optional.ofNullable(TICKET_MAP.get(id));
    }

    @Override
    public Collection<Ticket> findAll() {
        return TICKET_MAP.values();
    }

    @Override
    public void create(Ticket ticket) {
        TICKET_MAP.put(ticket.getId(), ticket);
    }

    @Override
    public boolean updateById(Ticket ticket) {
        TICKET_MAP.put(ticket.getId(), ticket);
        return true;
    }

    @Override
    public void deleteById(Ticket ticket) {
        TICKET_MAP.remove(ticket.getId());
    }

    @Override
    public long countByFinished(boolean finished) {
        long finishedCount = 0;
        for (Ticket ticket : TICKET_MAP.values()) {
            if(finished == ticket.isFinished()) {
                finishedCount++;
            }
        }
        return finishedCount;
    }
}
