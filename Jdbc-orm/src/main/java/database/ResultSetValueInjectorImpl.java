package database;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import reflection.MethodReflection;
import sqlBuilder.SqlParams;

public class ResultSetValueInjectorImpl implements ResultSetValueInjectior {

  /**
   * inject object if has relation, execute a second query and injection
   *
   * @throws SQLException
   */
  @Override
  public <T> List<T> inject(ResultSet rs, Class<T> clz) throws Exception {
    Field[] fields = clz.getDeclaredFields();
    List<T> result = new ArrayList<>();

    while (rs.next()) {
      T obj = clz.newInstance();

      for (Field field : fields) {
        if(SqlParams.isRelationField(field)) continue;
        String fieldName = field.getName();
        Object value = rs.getObject(fieldName);
        MethodReflection.setValue(obj, fieldName, value);
      }

      result.add(obj);
    }

    return result.size() == 0 ? null : result;
  }
}
