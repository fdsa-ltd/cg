package ${dict('project.package','ltd.fdsa.client')}.repository.writer;

import ${dict('project.package','ltd.fdsa.client')}.entity.${pascal_case(entity.name)};
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException; 
import lombok.var;
import ${dict('project.package','ltd.fdsa.client')}.entity.${pascal_case(entity.name)};
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
public class ${pascal_case(entity.name)}Writer
{
 
    @Autowired
    DataSource ds;

    public int insert(${pascal_case(entity.name)}... entities) {
        try {
            // 准备SQL语句
            String sql = "INSERT INTO ${snake_case(entity.code)} (${(snake_keys + snake_fields)?join(',\\n')}\n) VALUES (\n<#list 1..((snake_keys + snake_fields)?size - 1) as i>?,\n</#list>?\n)";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                <#list entity.fields as field>
                var ${camel_case(field.name)} = entity.get${pascal_case(field.name)}();
                </#list>                
                result += qr.update( sql, ${(camel_keys+camel_fields)?join(',')});
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int update(${pascal_case(entity.name)}... entities) {
        try {
            // 准备SQL语句
            String sql = "UPDATE ${snake_case(entity.code)} SET ${snake_fields?join('=?,\\n','','=?\\n')} WHERE ${snake_keys?join('=? and \\n','','=? \\n')};";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                <#list entity.fields as field>
                var ${camel_case(field.name)} = entity.get${pascal_case(field.name)}();
                </#list>                
                result += qr.update( sql, ${(camel_fields + camel_keys)?join(',')});
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int delete(int... ids) {
        try {
            // 准备SQL语句
            String sql = "DELETE FROM ${snake_case(entity.code)} WHERE ${snake_keys?join('=? and \\n','','=? \\n')};";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var id : ids) {
                // 实例化QueryRunner并执行DML语句
                result += qr.update( sql, id);
            }
            return result;
        } catch (SQLException e) {
            return -1;
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
