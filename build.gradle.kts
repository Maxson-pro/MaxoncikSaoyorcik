plugins {
    java
    application
    // Плагин для автоматической настройки JavaFX
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        // Указываем твою 17-ю версию Java
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    // Главный класс, который будет запускаться
    mainClass.set("org.example.maxonciksaoyorcik.HelloApplication")
}

javafx {
    version = "17.0.10"
    // Подключаем модули, без которых HelloApplication не взлетит
    modules = listOf("javafx.controls", "javafx.fxml")
}

dependencies {
    // Библиотеки для тестов (пусть будут, чтобы не было ошибок)
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}

tasks.test {
    useJUnitPlatform()
}