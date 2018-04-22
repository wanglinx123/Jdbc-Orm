package sqlPart;

public class LimitSql extends AbstractSqlPart{
  

  @Override
  public String getSql() {
    return subSql.getSql() + " " + sql;
  }
}
