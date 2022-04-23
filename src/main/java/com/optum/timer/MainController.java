package com.optum.timer;

//import com.optum.timer.model.Activity;
//import com.optum.timer.service.ActivityService;
import com.optum.timer.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public String root(Model model) {
        Activity activity = new Activity();
        model.addAttribute("activity", activity);
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/activity")
    public String activity(Model model) {
        Activity activity = new Activity();
        model.addAttribute("activity", activity);
        return "activity";
    }

    @RequestMapping("/postActivity")
    public String postActivity() {
        System.out.println("HERE@");
        return "postActivity/index";
    }

    @RequestMapping("/navigate")
    public String navigate() {
        return "navigate";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

}



