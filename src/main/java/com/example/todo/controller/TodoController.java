package com.example.todo.controller;

import com.example.todo.entity.Todo;
import com.example.todo.entity.User;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/*
 * ToDoController
 * ToDoアプリの基本CRUD処理を担当するコンとローラークラス
 */
@Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    /*
     * ToDoの一覧を表示する
     */

    @GetMapping("/")
    public String list(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        List<Todo> todos = todoRepository.findByUser(user);
        model.addAttribute("todos", todos);
        return "list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "form";
    }

    @PostMapping("/add")
    public String addSubmit(@ModelAttribute Todo todo, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        todo.setUser(user);
        todoRepository.save(todo);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("todo", todoRepository.findById(id).orElseThrow());
        return "form";
    }

    @PostMapping("/edit")
    public String editSubmit(@ModelAttribute Todo todo, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        todo.setUser(user);
        todoRepository.save(todo);
        return "redirect:/";
    }

    /** 
     * 指定IDの ToDo TODOを削除する
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        todoRepository.deleteById(id);
        return "redirect:/";
    }

    // @GetMapping("/login")
    // public String login() {
    //     return "login";
    // }
}