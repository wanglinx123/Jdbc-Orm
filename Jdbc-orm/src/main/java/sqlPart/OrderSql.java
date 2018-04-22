package sqlPart;

public class OrderSql extends AbstractSqlPart{
  
  @Override
  public String getSql() {
    return subSql.getSql() + " " + sql;
  }
}
