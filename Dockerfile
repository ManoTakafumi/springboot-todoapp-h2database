# Java 17 ベースイメージを使う
FROM openjdk:17-jdk-slim

# 作業ディレクトリ作成
WORKDIR /app

# プロジェクトのファイルをコピー（必要なものだけ）
COPY . .

# ビルド（JAR作成） テストをスキップ
RUN ./mvnw clean package -DskipTests

# アプリを起動（JAR名はあなたのプロジェクトに合わせて変更）
CMD ["java", "-jar", "target/todo0603-0.0.1-SNAPSHOT.jar"]