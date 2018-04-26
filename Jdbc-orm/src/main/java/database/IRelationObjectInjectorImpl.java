package database;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import lombok.SneakyThrows;
import reflection.MethodReflectionUtil;

/**
 * Inject Relational Object (List or Single Object) into target Object.
 *
 */
public class IRelationObjectInjectorImpl implements IRelationObjectInjector{

  @SneakyThrows
  @Override
  public <T> void injectObject(T obj, Field field, Object relation) {
    if (relation == null) return;

      Method setter = MethodReflectionUtil.setterOf(field, obj.getClass());
      setter.invoke(obj, relation);
  }
}
