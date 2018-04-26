package test;

import java.util.Arrays;
import org.junit.Test;
import annotation.JoinColumn;
import pojo.Pet;
import sqlBuilder.SqlParamsUtil;

public class AnnoTest {
  private Class<?> petClz = Pet.class;

  @Test
  public void RelationAnnotationTest() {
    Arrays.stream(petClz.getDeclaredFields()).forEach(f -> {
      if(SqlParamsUtil.isRelationField(f)) {
        System.out.println(SqlParamsUtil.getRelationTypeEnum(f) ); 
        System.out.println(f.getAnnotation(JoinColumn.class).value() ); 
      }
    });
  }
}
