package kr.or.ddit.team.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.team.service.TeamService;
import lombok.RequiredArgsConstructor;

/**
*
* @author KHS
* @since 2025. 3. 13.
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      			수정자           수정내용
*  -----------   	-------------    ---------------------------
*  2025. 3. 13.     	KHS	          최초 생성
*
* </pre>
*/

@Controller
@RequiredArgsConstructor
@RequestMapping("/teamMember")
public class TeamMemberDeleteController {

	private final TeamService service;

    @GetMapping("/delete/{teamId}/{tmId}")  // 동적으로 경로를 받을 수 있도록 설정
    public ResponseEntity<Map<String, Object>> deleteTeamMember(
            @PathVariable("teamId") Long teamId,  // 경로에서 teamId 받기
            @PathVariable("tmId") Long tmId) {    // 경로에서 tmId 받기

        boolean isDeleted = service.deleteTeamMember(teamId, tmId);

        Map<String, Object> response = new HashMap<>();
        response.put("success", isDeleted);
        response.put("message", isDeleted ? "팀원이 삭제되었습니다." : "팀원 삭제에 실패했습니다.");

        return ResponseEntity.ok(response);
    }

}