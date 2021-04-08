package com.seonzone.book.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.swing.*;


@SpringBootApplication
/*스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정 @SpringBootApplication 의
위치부터 읽기 때문에 최 상단에 있어야 한다. */

public class Application { //메인 클래스
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
//seonzone