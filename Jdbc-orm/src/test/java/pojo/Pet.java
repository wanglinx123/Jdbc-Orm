package pojo;

import java.util.Date;
import annotation.Relation;
import static enumeration.EntityRelationType.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
  @Relation(ID)
  private Long id;

  private String name;
  private Date birthday;

  @Relation(Many2One)
  private User user;

  public Pet(String name, Date birthday) {
    this.name = name;
    this.birthday = birthday;
  }
}
