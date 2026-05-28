package com.example.todo.repository;

import com.example.todo.entity.Todo;
import com.example.todo.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    //指定ユーザーに紐づくToDoを全て取得する
    List<Todo> findByUser(User user);

    //タイトル検索（部分一致）
    List<Todo> findByUserAndTitleContaining(User user, String keyword);

    //
    List<Todo> findByUserAndCompleted(User user, boolean completed);

    //
    List<Todo> findByUserAndTitleContainingAndCompleted(
            User user,
            String keyword,
            boolean completed
    );

    List<Todo> findByUserOrderByUpdatedAtDesc(User user);

    List<Todo> findByUserAndCompletedOrderByUpdatedAtDesc(
            User user,
            boolean completed
    );

    List<Todo> findByUserAndTitleContainingOrderByUpdatedAtDesc(
            User user,
            String keyword
    );

    List<Todo> findByUserAndTitleContainingAndCompletedOrderByUpdatedAtDesc(
            User user,
            String keyword,
            boolean completed
    );
}