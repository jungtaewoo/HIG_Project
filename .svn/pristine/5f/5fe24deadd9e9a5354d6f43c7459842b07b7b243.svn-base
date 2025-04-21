package kr.or.ddit.team.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mybatis.mappers.team.TeamMapper;
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

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamMapper mapper;

	@Override
	public List<TeamVO> teamManageList() {
		return mapper.teamManageList();
	}

	@Override
	public TeamVO selectTeam(Long teamId) {
		return mapper.selectTeam(teamId);
	}

	@Override
	public void insertTeamItem(TeamVO teamManage) {
		mapper.insertTeamItem(teamManage);
	}

	@Override
	public void updateTeamStatusItem(TeamVO teamManage) {
		mapper.updateTeamStatusItem(teamManage);
	}

	@Override
	public void deleteTeam(String teamId) {
		mapper.deleteTeam(teamId);
	}



    @Override
    @Transactional
    public int insertTeamMember(TeamMemberVO teamMemVO) {
        // 팀 멤버를 추가하는 로직
        // 예: DB에 삽입/업데이트 작업 수행
        // 작업 성공 시 1, 실패 시 0 반환

    	// 예시: 삽입 성공하면 1 반환
        int cnt = mapper.insertTeamMember(teamMemVO);
        System.out.println("======================================================================================= " + cnt);  // 반환된 cnt 값 확인
        System.out.println("======================================================================================= " + cnt);  // 반환된 cnt 값 확인
        System.out.println("(서비스임플)insertTeamMembers 결과 cnt: " + cnt);  // 반환된 cnt 값 확인
        System.out.println("(서비스임플)insertTeamMembers 결과 cnt: " + cnt);  // 반환된 cnt 값 확인
        System.out.println("======================================================================================= " + cnt);  // 반환된 cnt 값 확인
        System.out.println("======================================================================================= " + cnt);  // 반환된 cnt 값 확인
        return cnt;
    }

	// 예시 메서드: 현재 DB상의 TM_HR 값을 가져오는 로직 (구현은 상황에 따라 다름)
    private String getCurrentTmHr(TeamMemberVO member) {
        // 실제 DB 또는 캐시에서 현재 TM_HR 값을 조회하는 로직이 필요
        // 여기서는 단순히 member 객체의 값으로 대체 (실제와 다를 수 있음)
        return member.getTmHr();
    }

	@Override
	public boolean deleteTeamMember(Long teamId, Long tmId) {
		return mapper.deleteTeamMember(teamId, tmId);
	}



    @Override
	public void deleteTeams(List<String> teamIds) {
		mapper.deleteTeams(teamIds);

	}

	@Override
    @Transactional
    public void createTeamWithMembers(TeamVO team) {
        // 1. 팀 등록 (teamId 생성)
        mapper.insertTeam(team);

        // 2. 팀 멤버들의 tmId가 null인지 체크
//        for (TeamMemberVO member : team.getTeamMembers()) {
//            if (member.getTmId() == null) {
//                throw new IllegalArgumentException("TM_ID는 필수 입력값입니다!");
//            }
//            member.setTeamId(team.getTeamId()); // 팀 ID 설정
//        }
//
//        // 3. 팀 멤버들 등록
//        mapper.insertTeamMembers(team.getTeamMembers());
    }

	@Override
	public int insertTeamMembers(TeamMemberVO teamMemVO) {
		return mapper.insertTeamMember(teamMemVO);
	}

	// 팀 업데이트에서 팀원id의 팀장 팀원 여부 수정
	public void updateTeamMemberHR(TeamMemberVO teamMemVO) {
		mapper.updateTeamMemberHR(teamMemVO);
	}

    @Override
    @Transactional
    public void updateTeamWithMembers(TeamVO team) {
    	mapper.updateTeam(team);
    }

//    @Override
//    @Transactional
//    public void updateTeamWithMembers(TeamVO team) {
//        try {
//            // 1. 팀 정보 업데이트
//            mapper.updateTeam(team);
//
//            if (team.getTeamMembers() != null && !team.getTeamMembers().isEmpty()) {
//                // 기존 팀원 중 수정할 항목만 필터링
//                List<TeamMemberVO> updateList = team.getTeamMembers().stream()
//                    .filter(member -> member.getTmId() != null &&
//                            (member.getNewTmId() == null || member.getTmId().equals(member.getNewTmId())) &&
//                            !member.getTmHr().equals(getCurrentTmHr(member))) // 현재 팀원 상태와 달라야 업데이트
//                    .collect(Collectors.toList());
//
//                // 신규 팀원 추가 (tmId가 없는 경우)
//                List<TeamMemberVO> insertList = team.getTeamMembers().stream()
//                    .filter(member -> member.getTmId() == null && member.getNewTmId() != null)
//                    .collect(Collectors.toList());
//
//                // 기존 팀원 업데이트만 필요한 경우에만 호출
//                if (!updateList.isEmpty()) {
//                    mapper.updateTeamMembers(updateList);
//                }
//
//                // 신규 팀원 추가만 필요한 경우에만 호출
//                if (!insertList.isEmpty()) {
//                    mapper.insertTeamMembers(insertList);
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("팀 업데이트 중 오류 발생", e);
//        }
//    }






}
