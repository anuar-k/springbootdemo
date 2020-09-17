package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.User;
import com.example.springbootdemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    //внедряем зависимость с помощью конструктора
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //контроллер для вывода всех пользователей в user-list.html
    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    //контроллер вывода формы create_user.html для создания пользователя
    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "create_user";
    }

    //контроллер для создания создания пользователя
    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    //контроллер для удаления пользователя по id
    @GetMapping("/user-delete/{id}")
    public String deleteUserForm(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    //контроллер для обновления пользователя по id
    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    //контроллер для обновления пользователя
    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }
}