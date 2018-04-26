package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;

public class FieldReflctionUtil {

  public static Class<?> getGenericType(Field field) {
    return (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
  }

  /**
   * Get Bean Type of the Field.<br>
   * {@code List<User> -> User} <br>
   * {@code User -> User}.<br>
   * @param field
   * @return
   */
  public static Class<?> getBeanTypeOfField(Field field) {
    Class<?> cls = field.getType();
    if (cls == List.class || cls == Set.class) return getGenericType(field);
    return cls;
  }
}
