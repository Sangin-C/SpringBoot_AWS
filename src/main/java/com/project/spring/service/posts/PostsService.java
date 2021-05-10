package com.project.spring.service.posts;


import com.project.spring.domain.posts.Posts;
import com.project.spring.domain.posts.PostsRepository;
import com.project.spring.web.dto.PostsResponseDto;
import com.project.spring.web.dto.PostsSaveRequestDto;
import com.project.spring.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //update기능에서 DB쿼리를 날리는 부분이 없다
    //이게 가능한 이유는 JPA의 영속성 컨텍스트 때문이다.
    //영속성 컨텍스트란, 엔티티를 영구 저장하는 환경이다
    //JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈린다
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
}
