package test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import org.junit.Test;
import pojo.Pet;
import reflection.MethodReflection;

public class ReflectionTest {
  
  private Class<?> dogClz = Pet.class;
  
  @Test
  public void intro_test() {
    try {
      BeanInfo dogInfo = Introspector.getBeanInfo(dogClz);
      PropertyDescriptor[] descriptors =  dogInfo.getPropertyDescriptors();
      Arrays.stream(descriptors).forEach(System.out::println);
    } catch (IntrospectionException e) {
      e.printStackTrace();
    }
  }
  
  @Test
  public void getValue_test() {
    Object obj =  MethodReflection.getValue(new Pet("zhi", new Date()), "birthday");
    java.sql.Date date = new java.sql.Date(new Date().getTime());
    System.out.println(date.toLocalDate() ); 
    System.out.println(obj.getClass() );
    System.out.println(obj ); 
  }
}
