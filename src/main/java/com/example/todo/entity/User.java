package com.example.todo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "`users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "ユーザーネームを入力してください")
    @Size(max = 30, message = "ユーザー名は30文字以内です")
    @Column(unique = true, nullable = false)
    private String username;//ユーザー名

    @NotBlank(message = "パスワードを入力してください")
    @Size(min = 8, message = "パスワードは8文字以上入力してください")
    @Column(nullable = false)
    private String password;//ハッシュ化されたパスワード

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos;//ユーザーに属するTodoリスト

    //getter, setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}