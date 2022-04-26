package com.optum.timer.controller;

import com.optum.timer.model.Activity;
import com.optum.timer.model.User;
import com.optum.timer.repository.ActivityRepository;
import com.optum.timer.repository.UserRepository;
import com.optum.timer.service.ActivityService;
import com.optum.timer.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.Principal;

@Controller	// This means that this class is a Controller
public class ActivityController {


     // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private ActivityRepository activityRepository;

    private UserRepository userRepository;

    private ActivityService activityService;

    private UserDetails userDetails;

    @Autowired
    public ActivityController(ActivityRepository activityRepository, UserRepository userRepository, ActivityService activityService, UserDetails userDetails) {
        this.activityRepository = activityRepository;
        this.activityService = activityService;
        this.userRepository = userRepository;
        this.userDetails = userDetails;
    }

    @GetMapping("/addNewActivity")
    public String addNewActivity(Model model, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        Activity activity = new Activity();
        model.addAttribute("activity", activityService.getActivitiesById(user.getId()));
        return "index";
    }


    @PostMapping("/addNewActivity")
    public String saveNewActivity(@ModelAttribute("activity")Activity activity, Principal principal) {
        System.out.println("HERETOOO" + activity.getIntention());
        User user = userRepository.findByEmail(principal.getName());
        System.out.println(user);
        activityService.save(activity);
        user.getActivities().add(activity);
        userDetails.saveUser(user);
//        model.addAttribute("activity", activity);
        return "redirect:/";
    }

//    @PostMapping(path="/add") // Map ONLY POST Requests
//    public @ResponseBody String addNewActivity (@RequestParam long activityId
//            , @RequestParam long userId, @RequestParam String intention, @RequestParam int minutes, @RequestParam int seconds) {
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//
//        Activity newActivity = new Activity();
//        newActivity.setActivityId(activityId);
//        newActivity.setUserId(userId);
//        newActivity.setIntention(intention);
//        newActivity.setMinutes(minutes);
//        newActivity.setSeconds(seconds);
//        activityRepository.save(newActivity);
//        return "Saved";
//    }
//
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Activity> getAllActivities() {
        // This returns a JSON or XML with the users
        return activityRepository.findAll();
    }
}