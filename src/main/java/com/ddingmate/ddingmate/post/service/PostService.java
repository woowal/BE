package com.ddingmate.ddingmate.post.service;

import com.ddingmate.ddingmate.mark.domain.Mark;
import com.ddingmate.ddingmate.mark.repository.MarkRepository;
import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.service.MemberService;
import com.ddingmate.ddingmate.post.domain.Post;
import com.ddingmate.ddingmate.post.dto.request.PostCreateRequest;
import com.ddingmate.ddingmate.post.dto.request.PostUpdateRequest;
import com.ddingmate.ddingmate.post.dto.response.PostResponse;
import com.ddingmate.ddingmate.post.repository.PostRepository;
import com.ddingmate.ddingmate.post.state.Category;
import com.ddingmate.ddingmate.post.state.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;
    private final MarkRepository markRepository;

    @Transactional
    public void createPost(Long memberId, PostCreateRequest postCreateRequest) {
        Member member = memberService.findMemberById(memberId);
        Post post = postCreateRequest.toEntity(member);
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public Boolean updatePost(Long postId, PostUpdateRequest postUpdateRequest) {
        Post post = findPostById(postId);
        post.update(postUpdateRequest);
        return true;
    }

    @Transactional(readOnly = true)
    public List<PostResponse> retrieveAll() {
        return postRepository.findAll().stream()
                .map((post) -> PostResponse.from(post))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponse retrievePost(Long postId) {
        Post post = findPostById(postId);
        return PostResponse.from(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> retrievePostsByCategory(String categoryValue) {
        Category category = Category.valueOf(categoryValue);
        return postRepository.findAllByCategory(category)
                .stream()
                .map((post) -> PostResponse.from(post))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> retrievePostsByMark(Long memberId) {
        Member member = memberService.findMemberById(memberId);
        List<Mark> marks = markRepository.findAllByMember(member);

        return marks.stream()
                .map(Mark::getPost)
                .map((post) -> PostResponse.from(post))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> retrievePostsByType(String typeValue) {
        Type type = Type.valueOf(typeValue);
        return postRepository.findAllByType(type)
                .stream()
                .map((post) -> PostResponse.from(post))
                .collect(Collectors.toList());
    }

    public Post findPostById(Long postId) {
        return postRepository.findById(postId).get();
    }
}
