package kr.or.ddit.file.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 
 * @author CHOI
 * @since 2025. 3. 12.
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일               수정자           수정내용
 *  -----------      -------------    ---------------------------
 *  2025. 3. 12.        CHOI            파일Info에서 처리 가능
 *
 *      </pre>
 */
@Component
@PropertySource("classpath:kr/or/ddit/FileInfo.properties")
public class FileInfoVO {
	@Value("${maxFileSize}")
	private int maxFileSize; // 최대 파일 크기 (MB)

	@Value("${maxRequestSize}")
	private int maxRequestSize; // 최대 요청 크기 (MB)

	@Value("${fileImages}")
	private String fileImages; // 공통으로 저장될 폴더명

	@Value("${fileUploads}")
	private String fileUploads; // 파일 업로드 폴더명
	
	public String getFileUploads() {
		return fileUploads;
	}

	public void setFileUploads(String fileUploads) {
		this.fileUploads = fileUploads;
	}

	public int getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(int maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public int getMaxRequestSize() {
		return maxRequestSize;
	}

	public void setMaxRequestSize(int maxRequestSize) {
		this.maxRequestSize = maxRequestSize;
	}

	public String getFileImages() {
	    return fileImages == null ? null : fileImages.trim();
	}


	public void setFileImages(String fileImages) {
		this.fileImages = fileImages;
	}

	@Override
	public String toString() {
		return "FileInfoVO [maxFileSize=" + maxFileSize + ", maxRequestSize=" + maxRequestSize + ", fileImages="
				+ fileImages + ", fileUploads=" + fileUploads + '\'' + "]";
	}
	

}
