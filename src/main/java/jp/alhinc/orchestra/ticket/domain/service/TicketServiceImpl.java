package jp.alhinc.orchestra.ticket.domain.service;

import jp.alhinc.orchestra.ticket.domain.model.Ticket;
import jp.alhinc.orchestra.ticket.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    // 最大件数
    private static final long MAX_UNFINISHED_COUNT = 5;

    @Autowired
    TicketMapper ticketMapper;

    /**
     * 1.再利用性のため
     * 2.Null処理のラップ
     * @param id
     * @return
     */
    private Ticket findOne(String id) {
        Ticket ticket = (Ticket) ticketMapper.findById(id)
                .orElseThrow(() -> new RuntimeException());
        return ticket;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Ticket> findAll() {
        return ticketMapper.findAll();
    }

    @Override
    public Ticket create(Ticket ticket) {
        long unfinishedCount = ticketMapper.countByFinished(false);
        if(unfinishedCount >= MAX_UNFINISHED_COUNT) {
            throw new RuntimeException();
        }
        ticket.setId(UUID.randomUUID().toString());
        ticket.setCreatedAt(new Date());
        ticket.setFinished(false);

        ticketMapper.create(ticket);

        return ticket;
    }

    @Override
    public Ticket finish(String id) {
        Ticket ticket = findOne(id);
        if(ticket.isFinished()) {
            // TODO
            throw new RuntimeException();
        }

        ticket.setFinished(true);

        ticketMapper.updateById(ticket);

        return ticket;
    }

    @Override
    public void delete(String id) {
        Ticket ticket = findOne(id);
        ticketMapper.deleteById(ticket);
    }
}
