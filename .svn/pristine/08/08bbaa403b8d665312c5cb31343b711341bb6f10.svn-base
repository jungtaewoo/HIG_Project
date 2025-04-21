package kr.or.ddit.mybatis.mappers.account;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import kr.or.ddit.CustomRootContextConfig;



/**
 * 매퍼 스캔 테스트
 */
@CustomRootContextConfig
public class MapperScanTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SqlSessionFactoryBean sqlSessionFactoryBean;

    @Test
    void testMapperScan() throws Exception {
        // SqlSessionFactoryBean이 Spring Bean으로 등록되어 있는지 확인
//        assertNotNull(sqlSessionFactoryBean, "SqlSessionFactoryBean이 등록되지 않았습니다.");

        // SqlSessionFactoryBean에서 SqlSessionFactory를 가져옴
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
//        assertNotNull(sqlSessionFactory, "SqlSessionFactory가 생성되지 않았습니다.");

        // SqlSession을 열어서 AccountMapper가 정상적으로 매핑되는지 확인
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AccountMapper mapper = session.getMapper(AccountMapper.class);
//            assertNotNull(mapper, "AccountMapper가 MyBatis의 SqlSession에서 정상적으로 매핑되지 않았습니다.");
        }
    }
}
