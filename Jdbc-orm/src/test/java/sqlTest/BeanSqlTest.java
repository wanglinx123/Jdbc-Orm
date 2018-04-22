package sqlTest;

import static org.junit.Assert.assertSame;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import database.BeanDbConnector;
import database.DefaultDataSource;
import pojo.Pet;
import pojo.User;
import sqlBuilder.SqlBuilder;

public class BeanSqlTest {
  
  private  SqlBuilder builder = new SqlBuilder();
  private BeanDbConnector connector = new BeanDbConnector();
  
  @Before
  public void init() {
    connector.setDataSource(new DefaultDataSource());
  }
  
  @Test
  public void select_test() throws Exception {
    Pet pet = new Pet(null, null, null, new User("nonono"));
    List<Pet> pets =  connector.select(pet);
    pets.forEach(System.out::println);
  }
  
  @Test
  public void update_test() {
    Pet pet = new Pet(5L,  "nulllll", null, new User("nonono"));
    int result =  connector.update(pet);
    assertSame(1, result);
  }
  
  @Test
  public void insert_test() {
    Pet pet = new Pet(null,  "newwww", new Date(), null);
    int result =  connector.insert(pet);
    assertSame(1, result);
  }
}
