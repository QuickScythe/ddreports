package me.ddreports.server;

import jakarta.servlet.http.HttpSession;
import json2.JSONObject;
import me.ddreports.data.Dash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

@Controller
public class DataController {

    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @GetMapping("/app/{page}")
    public String appPage(@PathVariable("page") String page, Model model, HttpSession session) {
        return page;
    }

    @GetMapping("/data/chart")
    public String chart(Model model, HttpSession session) {
        model.addAttribute("data", new int[]{10, 20, 30, 40, 50});
        return "chart";
    }

    @GetMapping("/data/login")
    public String login(HttpSession session) {
        session.setAttribute("user", "admin");
        // Example data, replace with your actual data source
        return "redirect:/";
    }

    @GetMapping("/data/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/data/save-dash")
    public String saveDash(Model model) {
        model.addAttribute("form", new Dash());
        return "save-dash";
    }

    @PostMapping("/form/save-dash")
    public String saveDashPost(@ModelAttribute Dash form, Model model, HttpSession session) {
//        logger.info("Received JSON: {}", form);
//        logger.info(new JSONObject(form).toString(2));
        model.addAttribute("form", form);
        File file = new File("dashes/" + session.getAttribute("user").toString() + "/dash" + new Random().nextInt() + ".json");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(new JSONObject(form).toString(2));
            System.out.println("File written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/data/save-dash";
    }

    @GetMapping("/style.css")
    public String style(Model model) {
        model.addAttribute("backgroundColor", "#2b2b2b");
        model.addAttribute("textColor", "#dddddd");
        return "core.css";
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/data/login";
        }
        model.addAttribute("user", session.getAttribute("user"));
        return "index";
    }
}
