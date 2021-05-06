package com.project.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//프로젝트의 메인 클래스
//스프링 부트의 자동 설정, 스프링 Bean읽기와 생성 모두 자동으로 설정된다.
//@SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트의 최상단에 위치해야함
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args); //내장 WAS를 실행한다.
        //스프링 부트에서는 내장 WAS를 사용하는 것을 권장하고 있다.
        //'언제 어디서나 같은 환경에서 스프링 부트를 배포'할 수 있기 때문이다.
    }
}
