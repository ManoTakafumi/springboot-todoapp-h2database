package com.example.todo.controller;

import com.example.todo.entity.Todo;
import com.example.todo.entity.User;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "status", defaultValue = "all") String status,
            Model model, Principal principal) {

        String username = principal.getName();

        User user = userRepository.findByUsername(username).orElseThrow();

        List<Todo> todos;

        boolean hasKeyword = keyword != null && !keyword.isBlank();

        long totalCount = todoRepository.countByUser(user);

        long activeCount = todoRepository.countByUserAndCompleted(
                user,
                false
        );

        long completedCount = todoRepository.countByUserAndCompleted(
                user,
                true
        );

        switch (status) {

            //完了のみ
            case "completed":

                if (hasKeyword) {

                    todos = todoRepository
                            .findByUserAndTitleContainingAndCompletedOrderByPriorityAscCreatedAtAsc(
                                    user,
                                    keyword,
                                    true);
                } else {

                    todos = todoRepository
                            .findByUserAndCompletedOrderByUpdatedAtDesc(user, true);
                }

                break;

            //未完了のみ
            case "active":

                if (hasKeyword) {

                    todos = todoRepository
                            .findByUserAndTitleContainingAndCompletedOrderByPriorityAscCreatedAtAsc(
                                    user,
                                    keyword,
                                    false);

                } else {

                    todos = todoRepository
                            .findByUserAndCompletedOrderByPriorityAscCreatedAtAsc(user, false);
                }

                break;

            //全件
            default:

                if (hasKeyword) {

                    todos = todoRepository
                            .findByUserAndTitleContainingOrderByPriorityAscCreatedAtAsc(
                                    user,
                                    keyword);

                } else {

                    todos = todoRepository
                            .findByUserOrderByPriorityAscCreatedAtAsc(user);
                }
        }

        model.addAttribute("todos", todos);

        model.addAttribute("keyword", keyword);

        model.addAttribute("status", status);

        model.addAttribute("todoCount", todos.size());

        model.addAttribute("totalCount", totalCount);

        model.addAttribute("activeCount", activeCount);

        model.addAttribute("completedCount", completedCount);

//        //検索機能のみ（フィルタ機能追加前の処理）
//        if (keyword != null && !keyword.isBlank()) {
//
//            todos = todoRepository
//                    .findByUserAndTitleContaining(user, keyword);
//
//        } else {
//
//            todos = todoRepository.findByUser(user);
//        }
//
//        model.addAttribute("todos", todos);
//
//        //検索量保持
//        model.addAttribute("keyword", keyword);

        return "list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "form";
    }

    @PostMapping("/add")
    public String addSubmit(@Valid @ModelAttribute Todo todo,
                            BindingResult result,
                            Principal principal) {

        if (result.hasErrors()) {
            return "form";
        }

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

        Todo oldTodo = todoRepository
                .findById(todo.getId())
                .orElseThrow();

        oldTodo.setTitle(todo.getTitle());
        oldTodo.setCompleted(todo.isCompleted());
        oldTodo.setPriority(todo.getPriority());

        oldTodo.setUser(user);

        System.out.println("更新前: " + oldTodo.getCreatedAt());

        todoRepository.save(oldTodo);

        System.out.println("保存後: " + oldTodo.getCreatedAt());
        return "redirect:/";
    }

    /** 
     * 指定IDのTODOを削除する
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