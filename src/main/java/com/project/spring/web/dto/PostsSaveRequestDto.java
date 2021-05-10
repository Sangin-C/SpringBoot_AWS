package com.project.spring.web.dto;

import com.project.spring.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Entity 클래스와 거의 유사한 형태임에도 Dto 클래스를 추가로 생성한다
//Entity 클래스를 절대로 Request/Response 클래스로 사용해서는 안 된다
//Entity 클래스는 데이터베이스와 맞닿는 핵심 클래스이기 때문이다

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
