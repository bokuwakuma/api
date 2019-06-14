//package jp.alhinc.orchestra.api.user;
//
//import jp.alhinc.orchestra.ticket.domain.model.Ticket;
//import jp.alhinc.orchestra.ticket.domain.service.TicketService;
//import org.apache.catalina.mapper.Mapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@RestController
//@RequestMapping("api/v1/tickets")
//public class TicketRestController {
//
//    @Autowired
//    TicketService ticketService;
//
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<TicketResource> all() {
//        Collection<Ticket> ticketList = ticketService.findAll();
//        List<TicketResource> ticketResourceList = new ArrayList<>();
//        for (Ticket ticket : ticketList) {
////            ticketResourceList.add();
//        }
//        return ticketResourceList;
//    }
//}
