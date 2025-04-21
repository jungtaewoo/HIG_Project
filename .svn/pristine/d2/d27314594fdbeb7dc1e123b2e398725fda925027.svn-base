package kr.or.ddit.deptcomment.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.deptcomment.service.DeptCommentService;

@RestController
@RequestMapping("dept")
public class DeptCommentDeleteController {

    @Inject
    private DeptCommentService service;

    // 댓글 삭제 API (비동기)
    @PostMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteComment(@RequestBody CommentDeleteRequest request) {
        Map<String, String> response = new HashMap<>();

        try {
            Long result = service.deleteComment(request.getCommentId());

            if (result > 0) {
                response.put("message", "삭제 완료");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "삭제 실패");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "서버 오류 발생");
            return ResponseEntity.status(500).body(response);
        }
    }

    // 요청 데이터를 받을 DTO 클래스
    static class CommentDeleteRequest {
        private Long commentId;

        public Long getCommentId() {
            return commentId;
        }

        public void setCommentId(Long commentId) {
            this.commentId = commentId;
        }
    }
}
