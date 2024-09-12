package me.ddreports.server.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import json2.JSONObject;
import me.ddreports.data.Dash;
import me.ddreports.data.Store;
import me.ddreports.data.account.Account;
import me.ddreports.storage.StorageManager;
import me.ddreports.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String login(@RequestParam(value = "from", required = false, defaultValue = "/") String from, HttpSession session, Model model) {
        model.addAttribute("account", new Account());
        return "login";
    }

    @PostMapping("/form/login")
    public String loginPost(@ModelAttribute Account account, HttpSession session, HttpServletRequest request) {
        if (StorageManager.getAccountManager().getAccount(account.getUsername()) != null) {
            Account storedAccount = StorageManager.getAccountManager().getAccount(account.getUsername());
            if(account.getPassword().equals(storedAccount.getPassword())) {
                session.setAttribute("user", account.getUsername());
                request.getParameter("from");
                return "redirect:/";
            } else return "redirect:/data/login?error=badPassword";
        }
        return "redirect:/data/login?error=true";
    }

    @GetMapping("/data/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/data/register")
    public String register(HttpSession session, Model model) {
        model.addAttribute("account", new Account());
        return "register-account";
    }

    @PostMapping("/form/register")
    public String registerPost(@ModelAttribute Account account, HttpSession session) {
        if (StorageManager.getAccountManager().getAccount(account.getUsername()) == null) {
            StorageManager.getAccountManager().addAccount(account);
            session.setAttribute("user", account.getUsername());
            return "redirect:/";
        }
        return "redirect:/data/register?error=accountExists";

    }

    @GetMapping("/data/new-store")
    public String newStore(Model model, HttpSession session, HttpServletRequest request) {
        if (session.getAttribute("user") == null) {
            return "redirect:/data/login?from=" + request.getRequestURI();
        }
        model.addAttribute("store", new Store());
        return "new-store";
    }

    @PostMapping("/form/new-store")
    public String newStorePost(@ModelAttribute Store store, Model model, HttpSession session) {
        logger.info(new JSONObject(store).toString(2));
        model.addAttribute("store", store);
        StorageManager.saveStore(store);
//        StorageManager.saveStore(store, session.getAttribute("user").toString());
        return "redirect:/data/new-store";
    }

    @GetMapping("/data/save-dash")
    public String saveDash(Model model, HttpSession session, HttpServletRequest request) {
        if (session.getAttribute("user") == null) {
            return "redirect:/data/login?from=" + request.getRequestURI();
        }
        model.addAttribute("stores", StorageManager.getStores());
        model.addAttribute("form", new Dash());
        return "save-dash";
    }

    @PostMapping("/form/save-dash")
    public String saveDashPost(@ModelAttribute Dash form, Model model, HttpSession session) {
//        logger.info("Received JSON: {}", form);
//        logger.info(new JSONObject(form).toString(2));
        model.addAttribute("form", form);
        Account account = StorageManager.getAccountManager().getAccount(session.getAttribute("user").toString());
        if (account != null) {
            account.saveDash(form);
        } else return "redirect:/error";
        return "redirect:/data/save-dash";
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
