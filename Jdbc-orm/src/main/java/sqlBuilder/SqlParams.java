package sqlBuilder;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import annotation.Relation;
import conainer.Pair;
import enumeration.EntityRelationType;
import reflection.MethodReflection;

public class SqlParams {

  private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

  public static <T> Map<String, String> nonNullParams(T obj) throws Exception {
    Map<String, String> result = nonRelationParams(obj);

   return result
        .entrySet()
        .stream()
        .filter(e -> e.getValue() == null)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  public static <T> Map<String, String> nonRelationParams(T obj) throws Exception {
    Field[] fields = obj.getClass().getDeclaredFields();
    Map<String, String> result = new HashMap<>();

    for (Field f : fields) {
      if (isRelationField(f)) continue;
      Object val = MethodReflection.getValue(obj, f);
      if (val instanceof Date) val = formatter.format(val);
      if (val instanceof String) val = String.format("'%s'", val);
      result.put(f.getName(), val == null ? "null" : val.toString());
    }
    return result;
  }

  public static <T> List<Field> relationParams(T obj) {
    return null;
  }

  public static boolean isRelationField(Field field) {
    Relation r = field.getAnnotation(Relation.class);
    return r != null && r.value() != EntityRelationType.ID;
  }

  private static boolean isPrimaryKey(Field field) {
    Relation r = field.getAnnotation(Relation.class);
    return r != null && r.value() == EntityRelationType.ID;
  }

  public static <T> Pair<String, Object> getPrimaryKey(T obj) {
    Field[] fields = obj.getClass().getDeclaredFields();
    Pair<String, Object> pair = null;

    try {
      for (Field f : fields) {
        if (isPrimaryKey(f))
          pair = new Pair<String, Object>(f.getName(), MethodReflection.getValue(obj, f));
      }
      return pair;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
