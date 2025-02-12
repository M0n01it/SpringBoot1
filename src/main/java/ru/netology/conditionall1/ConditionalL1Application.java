package ru.netology.conditionall1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConditionalL1Application {

    public static void main(String[] args) {
        // Запускаем Spring Boot приложение и получаем контекст
        ConfigurableApplicationContext context = SpringApplication.run(ConditionalL1Application.class, args);

        // Получаем бин, реализующий интерфейс SystemProfile
        SystemProfile systemProfile = context.getBean(SystemProfile.class);

        // Выводим текущий профиль в консоль
        System.out.println(systemProfile.getProfile());
    }
}
