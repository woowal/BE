package com.ddingmate.ddingmate.comment.service;

import com.ddingmate.ddingmate.comment.domain.Comment;
import com.ddingmate.ddingmate.comment.dto.request.CreateCommentRequest;
import com.ddingmate.ddingmate.comment.dto.request.CreateReplyRequest;
import com.ddingmate.ddingmate.comment.repository.CommentRepository;
import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.service.MemberService;
import com.ddingmate.ddingmate.post.domain.Post;
import com.ddingmate.ddingmate.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.ddingmate.ddingmate.util.exception.ExceptionEnum.NO_SUCH_COMMENT;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final MemberService memberService;
    private final PostService postService;
    private final CommentRepository commentRepository;

    @Transactional
    public void createComment(Long memberId, CreateCommentRequest createCommentRequest) {
        Member member = memberService.retrieveMember(memberId);
        Post post = postService.retrievePost(createCommentRequest.getPostId());
        commentRepository.save(createCommentRequest.toEntity(member, post));
    }

    @Transactional
    public void createReply(Long id, Long memberId, CreateReplyRequest createReplyRequest) {
        Member member = memberService.retrieveMember(memberId);
        Comment parent = commentRepository.getReferenceById(id);
        Comment child = createReplyRequest.toEntity(member, parent);
        commentRepository.save(child);
    }

    public List<Comment> retrieveCommentByPost(Long postId) {
        Post post = postService.retrievePost(postId);
        return commentRepository.findAllByPost(post);
    }

    public List<Comment> retrieveCommentByMember(Long memberId) {
        Member member = memberService.retrieveMember(memberId);
        return commentRepository.findAllByMember(member);
    }

    @Transactional
    public void deleteCommentById(Long id) {
        Comment targetComment = commentRepository.getReferenceById(id);
        targetComment.deleteComment();
    }

    @Transactional
    public void updateComment(Long id, String content) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NoSuchElementException(NO_SUCH_COMMENT.getErrorMessage()));
        comment.update(content);
    }

}
