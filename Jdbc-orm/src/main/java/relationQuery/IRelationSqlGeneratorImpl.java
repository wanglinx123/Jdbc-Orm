package relationQuery;

import java.lang.reflect.Field;
import conainer.Pair;
import reflection.FieldReflctionUtil;
import sqlBuilder.SqlParamsUtil;

public class IRelationSqlGeneratorImpl implements IRelationSqlGenerator{

  @Override
  public <T> String one2ManySql(T obj, Field field) {
    String column = SqlParamsUtil.getJoinColumn(field);
    String oneIdVal = SqlParamsUtil.getPrimaryKeyValue(obj);
    Class<?> refClz = FieldReflctionUtil.getGenericType(field);
    String joinTable = SqlParamsUtil.getTableName(refClz);

    StringBuilder sb =
        new StringBuilder("select * from  ")
            .append(joinTable)
            .append(" where ")
            .append(column)
            .append(" = ")
            .append(oneIdVal);

    return sb.toString();
  }

  @Override
  public <T> String many2OneSql(T obj, Field field) {
    Class<?> refClz = field.getType();
    String joinPk = SqlParamsUtil.getPrimaryKeyName(refClz);
    String tarTable = SqlParamsUtil.getTableName(refClz);
    String srcTalbe = SqlParamsUtil.getTableName(obj);
    
    String fk = SqlParamsUtil.getJoinColumn(field);
    Pair<String, Object> pk =  SqlParamsUtil.getPrimaryKey(obj);
    
    //select tar.*
    // from a src inner join b tar
    //on src.tarid = tar.id
    //where src.id = ?

    StringBuilder sb =
        new StringBuilder("select tar.* from  ")
            .append(srcTalbe).append(" src inner join ")
            .append(tarTable).append(" tar on src.").append(fk)
            .append("=tar.").append(joinPk)
            .append(" where src.").append(pk.getFirst()).append("=").append(pk.getSecond().toString());

    return sb.toString();
  }
  
  @Override
  public <T> String one2OneSql(T obj, Field field) {
    Class<?> tarClz = field.getType();
    String tarTable = SqlParamsUtil.getTableName(tarClz);
    String srcTalbe = SqlParamsUtil.getTableName(obj);
    
    String fkTable = SqlParamsUtil.getJoinTable(field);
    if(fkTable == null) fkTable = srcTalbe;
    
    String fk = SqlParamsUtil.getJoinColumn(field);
    Pair<String, Object> srcIdPair = SqlParamsUtil.getPrimaryKey(obj);
    String srcId = srcIdPair.getFirst();
    
    StringBuilder sb = new StringBuilder("select tar.* from ")
        .append(srcTalbe).append(" src inner join ")
        .append(tarTable).append(" tar on src.");
    
    //fk can be in either src table or tar table
    if(srcTalbe.equalsIgnoreCase(fkTable)) {
      sb.append(fk).append("=tar.").append(SqlParamsUtil.getPrimaryKeyName(tarClz));
    }else {
      sb.append(srcId).append("=tar.").append(fk);
    }
    sb.append(" where src.").append(srcId).append("=").append(srcIdPair.getSecond());
    return sb.toString();
  }
}
