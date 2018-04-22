package sqlPart;

public abstract class AbstractSqlPart implements SqlPart{
  
  protected AbstractSqlPart subSql;
  protected String sql;
  
  protected AbstractSqlPart() {}
  
  protected AbstractSqlPart(AbstractSqlPart subSql) {
    this.subSql = subSql;
  }
}
