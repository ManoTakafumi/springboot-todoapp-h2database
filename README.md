# Spring Boot ToDoアプリ (H2版)

このアプリは、Java と Spring Boot を使って開発した ToDoリスト管理アプリです。
H2データベースを使用し、 Renderにデプロイして公開しています。
就職活動用ポートフォリオとして作成しました。

---

## 公開URL

Render: [https://springboot-todo-skpo.onrender.com](https://springboot-todo-skpo.onrender.com)

※Renderは無料プランのため、アプリの使用には事前に開発側での起動が必要です。

---

## 使用技術

- Java 17
- Spring Boot 3.x
- Spring Security
- Spring MVC / Thymeleaf
- Spring Data JPA
- H2 Database
- Maven
- Render (デプロイ)
- Docker (オプション)

---

## 主な機能

- ユーザー登録 / ログイン / ログアウト
- タスクの一覧表示
- タスクの追加 / 編集 / 削除
- タスクの完了チェック機能
- ユーザーごとにToDoを管理

---

# ディレクトリ構成(例)

```

src/
┝━━━━━main/
│ ┝━━━java/com/example/todo/
│ │┝━━━controller/
│ │┝━━━entity/
│ │┝━━━repository/
│ │┝━━━service/
| |└━━━TodoApplication.java
| └━━━resources/
|┝━━static/
|┝━━━templates/
|└━━━application.properties

```

---

## ローカルでの実行方法(H2使用)

# Spring Boot 実行

./mvnw spring-boot:run

---