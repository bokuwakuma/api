package jp.alhinc.orchestra.ticket.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {
    @RequestMapping("/")
    public String index(Model model) {
        System.out.println("====RootController====");
        model.addAttribute("message", "Hello, World!!!");
        return "index";
    }
}
