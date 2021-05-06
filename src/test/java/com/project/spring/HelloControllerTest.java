package com.project.spring;

import com.project.spring.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
//여기선 SpringRunner라는 스프링 실행자를 사용한다.
//즉, 스프링 부트 테스트와 JUnit사이에 연결자 역할을 한다.
@RunWith(SpringRunner.class)
//Web(Spring MVC)에 집중할 수 있는 어노테이션
//@Service, @Component, @Repository에는 사용 X
//컨트롤러만 사용하기 때문에 선언한다!
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    //스프링이 관리(IoC)하는 빈(Bean)을 주입 받는다.(DI)
    @Autowired
    //웹 API를 테스트할때 사용
    //스프링 MVC 테스트의 시작점
    //이클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있다.
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        //MockMvc를 통해 /hello 주소로 HTTP GET 요청을 한다.
        //체이닝이 지원되어 여러 검증 기능을 이어서 선언할 수 있다.
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) //Header의 Status를 검증한다.(200, 500 404 등)
                .andExpect(content().string(hello));    //응답 본문의 내용을 검증한다. (Controller에서 "hello"를 리턴하기에 똑같은지 확인)
    }


    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "test";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
        .param("name", name)    // .param -> API테스트할 때 사용될 요청 파라미터를 설정한다. 값은 String만 허용된다. 숫자/날짜 모두 문자열로 변경해야 가능하다.
        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))    //jsonPath -> JSON 응답값을 필드별로 검증할 수 있는 메소드이다. $를 기준으로 필드명을 명시한다.
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
