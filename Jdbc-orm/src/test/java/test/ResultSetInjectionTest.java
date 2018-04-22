package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import database.DbConnector;
import database.ResultSetValueInjectior;
import database.ResultSetValueInjectorImpl;
import pojo.User;

public class ResultSetInjectionTest {
  
  private DbConnector connector = new DbConnector();
  private ResultSetValueInjectior injector = new ResultSetValueInjectorImpl();
  private Connection conn;
  
  @Before
  public void init() {
    MysqlDataSource source = new MysqlDataSource();
    source.setUrl("jdbc:mysql://localhost:3306/pet");
    source.setEncoding("utf-8");
    source.setUser("root");
    source.setPassword("1995may16th");
    connector.setDataSource(source);
    conn = connector.getConnection();
  }
  
  @Test
  public void select_test() {
    ResultSet rs =  connector.query(conn, "select * from user");
    try {
      List<User> users = injector.inject(rs, User.class);
      users.forEach(System.out::println);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
