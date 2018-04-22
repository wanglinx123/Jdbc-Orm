package database;

import java.sql.ResultSet;
import java.util.List;

public interface ResultSetValueInjectior {
  <T> List<T> inject(ResultSet rs, Class<T> clz) throws Exception;
}
