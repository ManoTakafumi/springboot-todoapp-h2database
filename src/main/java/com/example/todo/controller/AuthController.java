package com.example.todo.controller;

import com.example.todo.entity.User;
import com.example.todo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/*
 * 認証・登録に関するコントローラー
 */
@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@Valid @ModelAttribute User user,
                                 BindingResult result,
                                 Model model) {

        //Validationのエラー
        if (result.hasErrors()) {
            return "register";
        }

        //ユーザー名重複チェック
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("error", "そのユーザーは既に使われています");
            return "register";
        }

        //パスワード暗号化
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login?registerSuccess";
    }
}