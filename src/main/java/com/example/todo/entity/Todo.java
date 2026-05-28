package com.example.todo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
// import lombok.Data;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "タイトルを入力してください")
    @Size(max = 100, message = "100文字以内で入力してください")
    private String title;

    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //作成日時
    private LocalDateTime createdAt;

    //更新日時
    private LocalDateTime updatedAt;

    /*
     * 初期保存時実行
     */
    @PrePersist
    public void onCreate() {

        LocalDateTime now = LocalDateTime.now();

        createdAt = now;
        updatedAt = now;
    }

    /*
     * 更新時に自動実行
     */
    @PreUpdate
    public void onUpdate() {

        updatedAt = LocalDateTime.now();
    }

    //getter, setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}