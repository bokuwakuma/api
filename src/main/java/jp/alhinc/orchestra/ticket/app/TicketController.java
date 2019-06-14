package jp.alhinc.orchestra.ticket.app;

import com.github.dozermapper.core.Mapper;
import jp.alhinc.orchestra.ticket.domain.model.Ticket;
import jp.alhinc.orchestra.ticket.domain.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    TicketService ticketService;

    @Autowired
    Mapper beanMapper;

    /**
     * This method equals "model.addAttribute("ticketForm", form)"
     * @return
     */
    @ModelAttribute
    public TicketForm setUpForm() {
        TicketForm form = new TicketForm();
        return form;
    }

    @RequestMapping("list")
    public String list(Model model) {
        logger.info("TicketController");
        Collection<Ticket> ticketList = ticketService.findAll();
        model.addAttribute("ticketList", ticketList);
        return "ticket/list";
    }

    @PostMapping("create")
    public String create(@Valid TicketForm ticketForm, BindingResult bindingResult,
                         Model model, RedirectAttributes attributes) {
        // Error Check
        if(bindingResult.hasErrors()) {
            return list(model);
        }

        Ticket ticket = beanMapper.map(ticketForm, Ticket.class);

        try {
            ticketService.create(ticket);
        } catch (RuntimeException e) {
            model.addAttribute("validationError", e.getMessage());
            return list(model);
        }

        attributes.addFlashAttribute("validationError", "Success");
        return "redirect:/ticket/list";
    }
}
