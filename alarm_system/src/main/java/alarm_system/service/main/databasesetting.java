package alarm_system.service.main;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.mapping.Environment;

import java.io.Reader;

import javax.sql.DataSource;

//db의 설정을 지정하기 위한 설정 클래스 파일이다.
//hikariCP와 mybatis를 활용한다 
public class databasesetting {
	private static SqlSessionFactory sqlSessionFactory;
	
	  static {
	        try {
	        	
	        	//db설정을 위한 db계정 및 세부 설정
	        	HikariConfig config = new HikariConfig();
	        	config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
	            config.setUsername("c##book_ex");
	            config.setPassword("book_ex");
	            config.setDriverClassName("oracle.jdbc.OracleDriver");
	            config.setMaximumPoolSize(10);
	            config.setMinimumIdle(5);
	            config.setConnectionTimeout(30000);
	            config.setIdleTimeout(600000);
	            config.setMaxLifetime(1800000);
	            
	            DataSource dataSource=new HikariDataSource(config);
	            Reader reader=Resources.getResourceAsReader("sql_config.xml");
	            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
	            
	            Environment env= new Environment("development",
	                    new JdbcTransactionFactory(), dataSource);
	           sqlSessionFactory.getConfiguration().setEnvironment(env);
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public static SqlSession getSession() {
	        return sqlSessionFactory.openSession();
	    }
}
