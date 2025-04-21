package kr.or.ddit.team.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.team.service.TeamService;
import lombok.extern.slf4j.Slf4j;

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

@Slf4j
@Controller
@RequestMapping("/team")
public class TeamDeleteController {

    @Autowired
    private TeamService service;

    @PostMapping("delete")
    @ResponseBody
    public Map<String, Object> teamDelete(@RequestBody List<String> teamIds) {
    	log.info("삭제 요청된 팀 ID 목록: {}", teamIds);

        Map<String, Object> response = new HashMap<>();

        try {
            // 삭제할 팀 ID 목록이 없으면 바로 실패 응답 반환
            if (teamIds == null || teamIds.isEmpty()) {
                response.put("success", false);
                response.put("message", "삭제할 팀을 선택하세요.");
                return response;
            }

            // 단 한 번만 삭제 작업을 실행합니다.
            service.deleteTeams(teamIds);

            response.put("success", true);
        } catch (Exception e) {
            log.error("팀 삭제 중 오류 발생: {}", e.getMessage(), e);
            response.put("success", false);
            response.put("message", "삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
        return response;
    }

    @PostMapping("deleteone")
    @ResponseBody
    public Map<String, Object> teamDelete(@RequestBody String teamId) {

    	Map<String, Object> response = new HashMap<>();

    	try {
    		// 삭제할 팀 ID 목록이 없으면 바로 실패 응답 반환
    		if (teamId == null || teamId.isEmpty()) {
    			response.put("success", false);
    			response.put("message", "삭제할 팀을 선택하세요.");
    			return response;
    		}

    		// 단 한 번만 삭제 작업을 실행합니다.
    		service.deleteTeam(teamId);

    		response.put("success", true);
    	} catch (Exception e) {
    		log.error("팀 삭제 중 오류 발생: {}", e.getMessage(), e);
    		response.put("success", false);
    		response.put("message", "삭제 중 오류가 발생했습니다: " + e.getMessage());
    	}
    	return response;
    }
}
