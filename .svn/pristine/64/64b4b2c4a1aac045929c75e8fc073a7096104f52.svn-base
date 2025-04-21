package kr.or.ddit;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.spring.config.RootContextJavaConfig;






@SpringJUnitWebConfig(RootContextJavaConfig.class)
@Retention(RUNTIME)
@Target(TYPE)
@Transactional
public @interface CustomRootContextConfig {
	
}
