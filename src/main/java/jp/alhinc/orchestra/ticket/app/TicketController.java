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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.groups.Default;
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
     *
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
    public String create(@Validated({Default.class, TicketForm.TicketCreate.class}) TicketForm ticketForm, BindingResult bindingResult,
                         Model model, RedirectAttributes attributes) {
        // Error Check
        if (bindingResult.hasErrors()) {
            return list(model);
        }

        Ticket ticket = beanMapper.map(ticketForm, Ticket.class);

        // for debug
        logger.info(ticket.toString());

        try {
            ticketService.create(ticket);
        } catch (RuntimeException e) {
            model.addAttribute("validationError", e.getMessage());
            return list(model);
        }

        attributes.addFlashAttribute("validationError", "Success");
        return "redirect:/ticket/list";
    }

    @PostMapping("finish")
    public String finish(@Validated({Default.class, TicketForm.TicketFinish.class}) TicketForm ticketForm,
                    BindingResult bindingResult, Model model, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("validationError", "完了できません");
            return list(model);
        }

        try {
            ticketService.finish(ticketForm.getId());
        } catch (RuntimeException e) {
            model.addAttribute("validationError", e.getMessage());
            return list(model);
        }

        attributes.addFlashAttribute("validationError", "Finished Success");
        return "redirect:/ticket/list";
    }

    @PostMapping("delete")
    public String delete(@Validated({Default.class, TicketForm.TicketDelete.class}) TicketForm ticketForm,
                         BindingResult bindingResult, Model model, RedirectAttributes attributes) {

        if(bindingResult.hasErrors()) {
            return list(model);
        }

        try {
            ticketService.delete(ticketForm.getId());
        } catch (RuntimeException e) {
            model.addAttribute("validationError", e.getMessage());
            return list(model);
        }

        attributes.addFlashAttribute("validationError", "Deleted Successfully");
        return "redirect:/ticket/list";
    }
}
