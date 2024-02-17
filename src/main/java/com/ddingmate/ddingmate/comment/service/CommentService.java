package com.ddingmate.ddingmate.comment.service;

import com.ddingmate.ddingmate.comment.domain.Comment;
import com.ddingmate.ddingmate.comment.dto.request.CreateCommentRequest;
import com.ddingmate.ddingmate.comment.dto.request.CreateReplyRequest;
import com.ddingmate.ddingmate.comment.dto.response.CommentResponse;
import com.ddingmate.ddingmate.comment.repository.CommentRepository;
import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.repository.MemberRepository;
import com.ddingmate.ddingmate.post.domain.Post;
import com.ddingmate.ddingmate.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void createComment(Long memberId, CreateCommentRequest createCommentRequest) {
        Member member = memberRepository.getReferenceById(memberId);
        Post post = postRepository.getReferenceById(createCommentRequest.getPostId());
        commentRepository.save(createCommentRequest.toEntity(member, post));
    }

    @Transactional
    public void createReply(Long id, Long memberId, CreateReplyRequest createReplyRequest) {
        Member member = memberRepository.getReferenceById(memberId);
        Comment parent = commentRepository.getReferenceById(id);
        Comment child = createReplyRequest.toEntity(member, parent);
        commentRepository.save(child);
    }

    public List<CommentResponse> retrieveCommentByPost(Long id) {
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getPost().getId().equals(id))
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }

    public List<CommentResponse> retrieveCommentByMember(Long id) {
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getMember().getId().equals(id))
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteCommentById(Long id) {
        if(commentRepository.findById(id).isPresent()) {
            commentRepository.deleteById(id);
        }
    }

    @Transactional
    public void updateComment(Long id, String content) {
        Comment comment = commentRepository.findById(id).get();
        comment.update(content);

//        if(comment.isPresent()) {
//            Comment targetComment = comment.get();
//            targetComment.update(content);
//        }
    }

    /*
    // TODO 검증의 책임과 반환의 책임을 분리하는 방법?
    private void checkCommentExist(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()) {

        }
    }
    */
}
