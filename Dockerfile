# ベースイメージ(OpenJDK 17)
FROM eclipse-temurin:17

# 作業ディレクトリ
WORKDIR /app

# JARファイルとコピー(ビルド後にtargetに生成される)
COPY target/*.jar app.jar

# アプリケーションを実行
ENTRYPOINT ["java", "-jar", "/app/app.jar"]