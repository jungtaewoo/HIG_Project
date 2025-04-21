package kr.or.ddit.mybatis.mappers.team;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.team.vo.TeamMemberVO;
import kr.or.ddit.team.vo.TeamVO;

@Mapper
public interface TeamMapper {
	// 팀 항목 리스트 조회
	public List<TeamVO> teamManageList();

	// 팀 상세보기
	public TeamVO selectTeam(Long teamId);

	// 항목 추가
	public void insertTeamItem(TeamVO teamManage);

	// 항목 상태 업데이트
	public void updateTeamStatusItem(TeamVO teamManage);

	// 팀 삭제
	public void deleteTeam(String teamId);

    // 팀 INSERT (자동 생성된 TEAM_ID를 TeamVO에 저장)
    public void insertTeam(TeamVO team);

    // 팀 멤버 INSERT (여러 개의 멤버를 한 번에 입력)
    public void insertTeamMembers(@Param("list") List<TeamMemberVO> teamMembers);

    // 팀 멤버 INSERT (한 명의 멤버를 입력)
    public int insertTeamMember(TeamMemberVO teamMemVO);

    // 팀 업데이트 (팀 이름, 부서 ID 등 수정)
    public void updateTeam(TeamVO team);

    // 팀 업데이트에서 팀원id의 팀장 팀원 여부 수정
    public void updateTeamMemberHR(TeamMemberVO teamMemVO);

    // 팀 멤버 업데이트 (예: 팀장 여부(TM_HR) 수정)
    public void updateTeamMembers(@Param("list") List<TeamMemberVO> teamMembers);

    // 팀멤버 삭제
    public boolean deleteTeamMember(@Param("teamId") Long teamId, @Param("tmId") Long tmId);

    // 팀 삭제
    public void deleteTeams(@Param("teamIds") List<String> teamIds);
}
