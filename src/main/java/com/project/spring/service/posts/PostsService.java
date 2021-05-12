package com.project.spring.service.posts;


import com.project.spring.domain.posts.Posts;
import com.project.spring.domain.posts.PostsRepository;
import com.project.spring.web.dto.PostsListResponseDto;
import com.project.spring.web.dto.PostsResponseDto;
import com.project.spring.web.dto.PostsSaveRequestDto;
import com.project.spring.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //update기능에서 DB쿼리를 날리는 부분이 없다.
    //이게 가능한 이유는 JPA의 영속성 컨텍스트 때문이다.
    //영속성 컨텍스트란, 엔티티를 영구 저장하는 환경이다.
    //JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈린다.
    //Entity 객체의 값만 변경하면 별도로 Update쿼리를 날릴 필요가 없다. 이것을 더티체킹이라고 한다.
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        return new PostsResponseDto(entity);
    }

    // (readOnly = true)를 주면 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선된다.
    // 등록,수정,삭제 기능이 전혀 없는 서비스 메소드에서 사용하는 것을 추천한다.
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                //.map(posts -> new PostsListResponseDto(posts))
                .collect(Collectors.toList());
    }
}
