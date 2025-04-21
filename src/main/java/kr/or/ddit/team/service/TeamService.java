package kr.or.ddit.team.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.team.vo.TeamMemberVO;
import kr.or.ddit.team.vo.TeamVO;

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

public interface TeamService {
		// 팀 리스트 조회
		public List<TeamVO> teamManageList();

		// 팀 상세보기
		public TeamVO selectTeam(Long teamId);

		// 팀 추가
		public void insertTeamItem(TeamVO teamManage);

		// 팀 상태 업데이트
		public void updateTeamStatusItem(TeamVO teamManage);

		// 팀 삭제
		public void deleteTeam(String teamId);

		// 팀 여러개 삭제
		public void deleteTeams(List<String> teamIds);

		// 팀 멤버 추가
		public int insertTeamMember(TeamMemberVO teamMemVO);

		// 팀 멤버 여러명 추가
		public int insertTeamMembers(TeamMemberVO teamMemVO);

		// 팀/팀멤버(팀장여부) 생성
		public void createTeamWithMembers(TeamVO team);

		// 팀 업데이트에서 팀원id의 팀장 팀원 여부 수정
		public void updateTeamMemberHR(TeamMemberVO teamMemVO);

		// 팀 멤버 삭제
		public boolean deleteTeamMember(Long teamId, Long tmId);


		// 팀/팀멤버 수정
		public void updateTeamWithMembers(TeamVO team);
}
