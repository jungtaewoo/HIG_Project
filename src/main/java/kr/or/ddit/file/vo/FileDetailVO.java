package kr.or.ddit.file.vo;



import lombok.Data;

@Data
public class FileDetailVO {
	private Long detailId; //첨부파일의 상세 아디
	private Long fileId; // 첨부파일의 고유식별자
	private String fileName; // 첨부파일의 원본명
	private String fileSavename; // 첨부파일의 저장명
	private String filePath; // 파일의 저장경로
	private String fileStatus; // 파일 상태 (N: 정상, Y: 삭제됨)
	private Long fileSize; // 파일 크기(KB,MB)
	private String fileType; // 파일의 확장자
	private String uploadDate; // 업로드날짜
	private FileType type;
	
	public static enum FileType {
		INLINE,
		ATTACHMENT
	}
}
