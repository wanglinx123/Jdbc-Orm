package annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import enumeration.EntityRelationType;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Relation {
  EntityRelationType value();
}
