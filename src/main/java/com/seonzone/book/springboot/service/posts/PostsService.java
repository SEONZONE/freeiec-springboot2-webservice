package com.seonzone.book.springboot.service.posts;

import com.seonzone.book.springboot.domain.posts.Posts;
import com.seonzone.book.springboot.domain.posts.PostsRepository;
import com.seonzone.book.springboot.web.dto.PostsListResponseDto;
import com.seonzone.book.springboot.web.dto.PostsResponseDto;
import com.seonzone.book.springboot.web.dto.PostsSaveRequestDto;
import com.seonzone.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
// @Transactional : 선언적 트랜잭션, 클래스, 메서드위에 @Transactional이 추가되면, 이 클래스에 트랜잭션 기능이 적용된 프록시 객체가 생성,
// 이 프록시 객체는 @Transactional이 포함된 메소드가 호출 될 경우, PlatfromTransactionManager를 사용하여 트랙잭션을 시작하고 정상 여부에 따라
//Commit 또는 Rollback 한다.
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;

    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
    /* findAllDesc 메소드의 트랜잭션 어노테이션(@Transactional)에 옵션이 하나 추가하였다.
     * (readOnly = true) 를 주면 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선되기 때문에 등록, 수정, 삭제 기능이 전혀 없는
     * 서비스 메소드에서 사용하는 것을 추천한다,
     * .map(PostListResponseDto::new)의 실제로 .map(posts -> new PoststListResponseDto(posts))
     * postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostListResponseDto 변환 -> List로 반환하는 메소드이다.
     *
     * */


    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        postsRepository.delete(posts);
        /*postsRepository.delete(posts)
         * JpaRepository에서 이미 delete 메소드를 지원하고 있으니 이를 활용
         * 엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id를 삭제 할 수 있다.
         * 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제 */
    }


    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
