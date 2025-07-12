package com.example.todo.repository;

import com.example.todo.entity.Todo;
import com.example.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    //指定ユーザーに紐づくToDoを全て取得する
    List<Todo> findByUser(User user);
}