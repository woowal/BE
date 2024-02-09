package com.ddingmate.ddingmate.post.service;

import com.ddingmate.ddingmate.like.domain.Like;
import com.ddingmate.ddingmate.like.repository.LikeRepository;
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
    private final LikeRepository likeRepository;

    @Transactional
    public void createPost(PostCreateRequest postCreateRequest) {
        Post post = postCreateRequest.toEntity();
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public void updatePost(PostUpdateRequest postUpdateRequest) {
        Post post = findPostById(postUpdateRequest.getPostId());
        post.update(postUpdateRequest);
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
        return postRepository.findAllByCategoryContains(category)
                .stream()
                .map((post) -> PostResponse.from(post))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> retrievePostsByLike(Long memberId) {
        Member member = memberService.findMemberById(memberId);
        List<Like> likes = likeRepository.findAllByMember(member);

        return likes.stream()
                .map(Like::getPost)
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
