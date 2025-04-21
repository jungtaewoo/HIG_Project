package kr.or.ddit.spring.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class RootContextOthersJavaConfig {
    
    /**
     * FileInfo.properties 파일 로드
     */
    @Bean
    public PropertiesFactoryBean fileInfo(
        @Value("classpath:kr/or/ddit/FileInfo.properties") Resource location
    ) {
        PropertiesFactoryBean factory = new PropertiesFactoryBean();
        factory.setLocation(location);
        return factory;
    }

    /**
     * 파일 업로드 설정 (MultipartResolver) - FileInfo.properties 값 주입
     */
    @Bean
    public MultipartResolver filterMultipartResolver(
        @Value("#{fileInfo['maxFileSize']}") long maxFileSize, // 단위: KB
        @Value("#{fileInfo['maxRequestSize']}") long maxRequestSize // 단위: KB
    ) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(maxRequestSize * 1024L * 1024L); // 전체 요청 크기 (KB → Bytes 변환)
        multipartResolver.setMaxUploadSizePerFile(maxFileSize * 1024L * 1024L); // 개별 파일 크기 제한 (KB → Bytes 변환)
        return multipartResolver;
    }

    /**
     * 파일 저장 경로를 Bean으로 등록하여 다른 설정에서 활용 가능하도록 함
     */
    @Bean
    public Properties fileStoragePaths(
        @Value("#{fileInfo['fileImages']}") String fileImagesPath,
        @Value("#{fileInfo['fileUploads']}") String fileUploadsPath
    ) {
        Properties properties = new Properties();
        properties.setProperty("fileImagesPath", fileImagesPath);
        properties.setProperty("fileUploadsPath", fileUploadsPath);
        return properties;
    }
    
    
    /**
     * PDF,메일전송시 필요 4/3추가(영준), 테스트용
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
    	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    	resolver.setPrefix("/WEB-INF/views/");
    	resolver.setSuffix(".jsp");
    	resolver.setOrder(2); // 낮은 우선순위 = 타일즈보다 뒤
    	return resolver;
    }


}
