package database;

import java.lang.reflect.Field;

public interface IRelationObjectInjector {
  <T> void injectObject(T obj, Field field, Object relation);
}
