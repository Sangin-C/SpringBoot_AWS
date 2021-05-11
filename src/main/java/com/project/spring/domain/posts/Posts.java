package com.project.spring.domain.posts;

import com.project.spring.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //클래스 내 모든 필드의 Getter 메소드를 생성
@NoArgsConstructor  //기본 생성자 자동 추가 - public Posts(){} 와 같은 효과
@Entity //테이블과 링크될 클래스임을 나타낸다. 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭한다.
public class Posts extends BaseTimeEntity {

    @Id //해당 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성 규칙 스프링 부트 2.0에서는 GenerationType.IDENTITY 추가해야 auto_increment가 된다.
    private Long id;

    @Column(length = 500, nullable = false) //Table컬럼임을 나타낸다. 기본값 외에 추가적으로 변경할 옵션이 있다면 사용해야한다.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private  String author;

    @Builder //해당 클래스의 빌더 패턴 클래스를 생성
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
