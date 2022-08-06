package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUserList(Model model) {
        List<User> users = userService.getListUsers();
        model.addAttribute("users", users);
        return "listUsers";
    }

    @GetMapping("/createUser")
    public String createUserFrom(User user, Model model) {
        model.addAttribute("user", user);
        return "createUser";
    }

    @PostMapping("/createUser")
    public String createUser(User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("deleteUser/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

}