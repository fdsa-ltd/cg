package ${setting('project.package','ltd.fdsa.client')}.repository.reader; 

import ${setting('project.package','ltd.fdsa.client')}.entity.${pascal_case(entity.name)};
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException; 
import lombok.var;
import ${setting('project.package','ltd.fdsa.client')}.entity.${pascal_case(entity.name)};
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.text.MessageFormat;

<#assign snake_fields=[] />
<#assign snake_keys=[] />
<#assign camel_fields=[] /> 
<#assign camel_keys=[] /> 
<#list entity.fields as field>
<#if field.primary>
<#assign snake_keys = snake_keys + [ '${snake_case(field.code)}' ] />
<#assign camel_keys = camel_keys + [ '${camel_case(field.name)}' ] />
<#else>
<#assign snake_fields = snake_fields + [ '${snake_case(field.code)}' ] />
<#assign camel_fields = camel_fields + [ '${camel_case(field.name)}' ] />
</#if>
</#list>
@Service
public class ${pascal_case(entity.name)}Reader
{
 
    @Autowired
    DataSource ds;

    public List<${pascal_case(entity.name)}> page(int index, int pageSize ,String order,String where, Object... param) {
        try {
            String limit =MessageFormat.format("LIMIT {0} OFFSET {1}", pageSize, pageSize * index);
            String sql = "SELECT ${(snake_keys + snake_fields)?join(',\\n')}\nFROM ${snake_case(entity.code)} "+
             where +
             order +
             limit;
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), param);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }


    public List<${pascal_case(entity.name)}> query(String where, Object... param) {
        try {
            String sql = "SELECT ${(snake_keys + snake_fields)?join(',\\n')}\nFROM ${snake_case(entity.code)} "+ where;
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), param);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
 
    public List<${pascal_case(entity.name)}> queryByPrimaryKey(Object key) {
        try {
            String sql = "SELECT ${(snake_keys + snake_fields)?join(',\\n')}\nFROM ${snake_case(entity.code)}  WHERE ${snake_keys?join('=? and \\n','','=? \\n')};";
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), key);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
    ResultSetHandler<List<${pascal_case(entity.name)}>> resultSetHandler() {
        return new ResultSetHandler<List<${pascal_case(entity.name)}>>() {
            @Override
            public List<${pascal_case(entity.name)}> handle(ResultSet rs) throws SQLException {
                List<${pascal_case(entity.name)}> list = new ArrayList<>(rs.getRow());
                while (rs.next()) {
                    ${pascal_case(entity.name)} entity = new ${pascal_case(entity.name)}();
                    <#list entity.fields as field>
                    <#switch field.type>
                    <#case 'String'>
                    entity.set${pascal_case(field.name)}(rs.getString("${snake_case(field.code)}"));
                    <#break>
                    <#case 'Integer'>
                    entity.set${pascal_case(field.name)}(rs.getInt("${snake_case(field.code)}"));
                    <#break>
                    <#case 'Date'>
                    entity.set${pascal_case(field.name)}(rs.getDate("${snake_case(field.code)}"));
                    <#break>
                    <#default>
                    // ${field.type} ;
                    </#switch>
                    </#list>
                    list.add(entity);
                }
                return list;
            }
        };
    }
}
