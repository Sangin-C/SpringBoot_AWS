package com.project.spring.dto;

import com.project.spring.web.dto.HelloResponseDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        //assertThat -> assertj라는 테스트 검증 라이브러리의 검증 메소드이다. 메소드 체이닝이 지원되 isEqualTo와 같이 메소드를 이어서 사용가능!
        //isEqualTo -> assertj의 동등 비교 메소드이다. assertThat의 값과 isEqualTo의 값이 같으면 성공!
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
