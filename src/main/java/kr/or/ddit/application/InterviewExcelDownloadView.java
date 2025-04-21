package kr.or.ddit.application;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import kr.or.ddit.application.vo.ApplicationVO;

/**
 * 선택된 면접자 목록을 엑셀로 다운로드하기 위한 View 클래스
 */
public class InterviewExcelDownloadView extends AbstractXlsxView {

    private final List<ApplicationVO> interviewList;

    // 생성자에서 데이터 주입
    public InterviewExcelDownloadView(List<ApplicationVO> interviewList) {
        this.interviewList = interviewList;
    }

    @Override
    protected void buildExcelDocument(
            java.util.Map<String, Object> model,
            Workbook workbook,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // 엑셀 시트 생성
        Sheet sheet = workbook.createSheet("합격자 정보");

        // 헤더 작성
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("지원공고명");
        header.createCell(1).setCellValue("지원자명");
        header.createCell(2).setCellValue("생년월일");
        header.createCell(3).setCellValue("성별");
        header.createCell(4).setCellValue("email");
        header.createCell(5).setCellValue("면접일자");
        header.createCell(6).setCellValue("전문지식");
        header.createCell(7).setCellValue("기술역량");
        header.createCell(8).setCellValue("태도");
        header.createCell(9).setCellValue("소통");
        header.createCell(10).setCellValue("경험");
        
        // 데이터 행 작성
        int rowNum = 1;
        for (ApplicationVO app : interviewList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(app.getRecruitment().getRecruitTitle());//지원공고명
            row.createCell(1).setCellValue(app.getAppName());//지원자명
            row.createCell(2).setCellValue(app.getAppYeardate());//생년월일
            row.createCell(3).setCellValue(app.getAppGender());//성별
            row.createCell(4).setCellValue(app.getAppEmail());//email
            row.createCell(5).setCellValue(app.getApplicationStatus().getInterviewDate().substring(0, 10));//면접일자
            row.createCell(6).setCellValue(app.getApplicationStatus().getEvalKnow());//전문지식
            row.createCell(7).setCellValue(app.getApplicationStatus().getEvalSkill());//기술역량
            row.createCell(8).setCellValue(app.getApplicationStatus().getEvalAtti());//태도
            row.createCell(9).setCellValue(app.getApplicationStatus().getEvalCommu());//소통
            row.createCell(10).setCellValue(app.getApplicationStatus().getEvalExperi());//경험
        }

        // 다운로드 응답 헤더 설정
        response.setHeader("Content-Disposition", "attachment;filename=passed_applicant_info.xlsx");
    }
}
