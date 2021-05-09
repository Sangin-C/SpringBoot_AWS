package com.project.spring.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository<Entity 클래스, PK 타입>을 상속하면 기본적인 CRUD 메소드가 자동으로 생성된다.
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
