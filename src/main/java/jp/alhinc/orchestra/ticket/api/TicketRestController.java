package jp.alhinc.orchestra.ticket.api;

import com.github.dozermapper.core.Mapper;
import jp.alhinc.orchestra.ticket.domain.model.Ticket;
import jp.alhinc.orchestra.ticket.domain.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * REST API for Ticket
 * <table>
 *     <tr><th>API name</th><th>HTTP method</th><th>path</th><th>Status code</th><th>subscription</th></tr>
 *     <tr><td>GET tickets</td><td>{@code /api/v1/tickets}</td><td>200</td><td>全件取得</td></tr>
 *
 * </table>
 * @author bokuwakuma
 */
@RestController
public class TicketRestController {

    private static final Logger logger = LoggerFactory.getLogger(TicketRestController.class);

    @Autowired
    TicketService ticketService;

    @Autowired
    Mapper beanMapper;

    /**
     * レコードを全件取得する
     * @return
     */
    @RequestMapping("tickets")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketResource> getTickets() {
        Collection<Ticket> tickets = ticketService.findAll();
        List<TicketResource> ticketResource = new ArrayList<>();
        for (Ticket ticket : tickets) {
            logger.info(ticket.toString());
            ticketResource.add(beanMapper.map(ticket, TicketResource.class));
        }
        return ticketResource;
    }

    /**
     * レコードを1件取得する
     * @param id
     * @return
     */
    @GetMapping("ticket/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TicketResource getTicket(@PathVariable Integer id) {
        Ticket ticket = ticketService.findOne(id);
        return beanMapper.map(ticket, TicketResource.class);
    }

    /**
     * レコードのステータスを完了済みに更新する
     * @param id
     * @return
     */
    @PutMapping("ticket/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TicketResource putTicket(@PathVariable Integer id) {
        Ticket finishedTicket = ticketService.finish(id);
        return beanMapper.map(finishedTicket, TicketResource.class);
    }

    /**
     * レコードを追加する
     * @param ticketResource
     * @return
     */
    @PostMapping("ticket/new")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResource createTicket(@RequestBody @Validated TicketResource ticketResource) {
        logger.info(beanMapper.map(ticketResource, Ticket.class).toString());
        Ticket createdTicket = ticketService.create(beanMapper.map(ticketResource, Ticket.class));
        // MappingJackson2HttpMessageConverter
        return beanMapper.map(createdTicket, TicketResource.class);
    }

    /**
     * レコードを削除する
     * @param id
     */
    @DeleteMapping("ticket/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable Integer id) {
        ticketService.delete(id);
    }
}