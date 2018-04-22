package database;

import java.sql.ResultSet;
import java.util.List;
import sqlBuilder.SqlBuilder;

public class BeanDbConnector extends DbConnector{
  
  private ResultSetValueInjectior injector = new ResultSetValueInjectorImpl();
  private SqlBuilder sqlBuilder = new SqlBuilder();
  
  public <T> List<T> select(T obj) {
    try {
      String sql = sqlBuilder.selectSql(obj);
      System.out.println(sql ); 
      ResultSet rs = query(getConnection(), sql);
      return (List<T>) injector.inject(rs, obj.getClass());
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
  
  public <T> int update(T obj) {
    try {
      String sql = sqlBuilder.updateSql(obj);
      System.out.println(sql ); 
      return update(getConnection(), sql);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
  
  public <T> int insert(T obj) {
    try {
      String sql = sqlBuilder.insertSql(obj);
      System.out.println(sql ); 
      return update(getConnection(), sql);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
