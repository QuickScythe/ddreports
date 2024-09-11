package me.ddreports.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StyleController {

    @GetMapping("/style.css")
    public String style(Model model) {
        model.addAttribute("backgroundColor", "#2b2b2b");
        model.addAttribute("textColor", "#dddddd");
        model.addAttribute("linkColor", "#ff7070");
        model.addAttribute("visitedLinkColor", "#ff7070");
        return "core.css";
    }
}
