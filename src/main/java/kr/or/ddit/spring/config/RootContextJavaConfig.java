  package kr.or.ddit.spring.config;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.aspectj.lang.annotation.Aspect;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 설정파일에서는 Model -> dao, service만 등록하기 위한 controller 배제 작업
 */
@Configuration
// 상위 컨테이너 생성 
@ComponentScan(basePackages = "kr.or.ddit"
	, excludeFilters = {
		@ComponentScan.Filter(classes = Controller.class)
	}
	, includeFilters = {
			@ComponentScan.Filter(classes = Aspect.class)
	}
)
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class RootContextJavaConfig {
	
	@Bean
	public PropertiesFactoryBean dbInfo(
		@Value("classpath:kr/or/ddit/db/DBInfo.properties") Resource location 
	) {
		PropertiesFactoryBean factory = new PropertiesFactoryBean();
		factory.setLocation(location);
		return factory;
	}
	
	@Bean
	public DataSource dataSource(
		@Value("#{dbInfo.driverClassName}") String driverClassName
		,@Value("#{dbInfo['url']}") String url
		,@Value("#{dbInfo.user}") String user
		,@Value("#{dbInfo.password}") String password
		,@Value("#{dbInfo.initialSize}") int initialSize
		,@Value("#{dbInfo.maxWait}") long maxWait
		,@Value("#{dbInfo.maxTotal}") int maxTotal
	) {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		//커넥션 여러개 생성 
		ds.setInitialSize(initialSize);
		ds.setMaxWait(Duration.of(maxWait/1000, ChronoUnit.SECONDS));
		ds.setMaxTotal(maxTotal);
		return ds;
	}
	
//Resource... mapperLocations
	/**
	 * 
	 * FactoryBean 의 특성
	 * FactoryBean 을 bean 으로 등록한 경우,
	 * 실제 bean으로 등록되는 객체는 getObject 메소드의 반환 객체.
	 * @param dataSource
	 * @param configLocation
	 * @return
	 */
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(
		DataSource dataSource 
		, @Value("classpath:kr/or/ddit/mybatis/Configuration.xml") Resource configLocation
		, @Value(
		        "classpath:kr/or/ddit/mybatis/mappers/**/*.xml"   
		  ) Resource... mapperLocations
			) {
		//SqlSessionFactoryFactoryBean
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setConfigLocation(configLocation);
		factoryBean.setMapperLocations(mapperLocations);
		//사이에 ** -> vo로 끝나는 모든 패키지들
		factoryBean.setTypeAliasesPackage("kr.or.ddit.**.vo");
		return factoryBean;
	}

	
	@Bean
	public MapperScannerConfigurer mapperScanner() {
		MapperScannerConfigurer configurar = new MapperScannerConfigurer();
		// 와일드카드 두개로 상위 컨테이너 셋팅 ** -> 패키지 두개 
		configurar.setBasePackage("kr.or.ddit.mybatis.mappers");
		// Mapper로 끝나는 인터페이스를 자동으로 등록해줌 -> proxy 자동 생성
		configurar.setAnnotationClass(Mapper.class);
		//sqlSessionFactory의 id값을 넘겨줌
		configurar.setSqlSessionFactoryBeanName("sqlSessionFactory");
		return configurar;
	}
	
	@Bean
	public TransactionManager transactionManager(
		DataSource dataSource 
	) {
		return new DataSourceTransactionManager(dataSource);
	}
}
