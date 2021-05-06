package com.project.spring.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter //선언된 모든 필드의 get메소드를 생성
@RequiredArgsConstructor //선언된 모든 필드 중 final 포함된 필드의 생성자를 만든다(final이 없으면 생성자에 포함하지 않는다)
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
