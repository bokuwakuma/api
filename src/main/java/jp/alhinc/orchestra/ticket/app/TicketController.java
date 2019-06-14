package jp.alhinc.orchestra.ticket.app;

import jp.alhinc.orchestra.ticket.domain.model.Ticket;
import jp.alhinc.orchestra.ticket.domain.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    TicketService ticketService;

    /**
     * This method equals "model.addAttribute("ticketForm", form)"
     * @return
     */
    @ModelAttribute
    public TicketForm setUpForm() {
        TicketForm form = new TicketForm();
        return form;
    }

    public String list(Model model) {
        logger.info("TicketController");
        Collection<Ticket> ticketList = ticketService.findAll();
        model.addAttribute("ticketList", ticketList);
        return "list";
    }

}
