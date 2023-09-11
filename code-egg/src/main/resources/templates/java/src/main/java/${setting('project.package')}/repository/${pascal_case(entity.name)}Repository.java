package ${setting('project.package','//todo add project.package')?replace('/','.')}.repository;

import ${setting('project.package','//todo add project.package')?replace('/','.')}.entity.${pascal_case(entity.name)};
import ${setting('project.package','//todo add project.package')?replace('/','.')}.enums.*;
import cn.zhumingwu.database.view.Page;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import jakarta.annotation.Nullable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

<#assign snake_fields=[] />
<#assign snake_keys=[] />
<#assign camel_fields=[] /> 
<#assign camel_keys=[] /> 
<#list entity.fields as field>
<#if field.primary>
<#assign snake_keys = snake_keys + [ '\\"${snake_case(field.code)}\\"' ] />
<#assign camel_keys = camel_keys + [ '${camel_case(field.name)}' ] />
<#else>
<#assign snake_fields = snake_fields + [ '\\"${snake_case(field.code)}\\"' ] />
<#assign camel_fields = camel_fields + [ '${camel_case(field.name)}' ] />
</#if>
</#list>
@Service
@Slf4j
public class ${pascal_case(entity.name)}Repository {

    static Cache<String, Expression> local = CacheBuilder.newBuilder().maximumSize(1024)
            .expireAfterAccess(10L, TimeUnit.MINUTES).removalListener(removalNotification -> {
            })
            .build();

    private final ExpressionParser parser = new SpelExpressionParser();
    @Resource
    NamedParameterJdbcTemplate jdbc;

    public List<${pascal_case(entity.name)}> page(int index, int pageSize, String order, String where, Object... param) {
        String limit = MessageFormat.format("LIMIT {0} OFFSET {1}", pageSize, pageSize * index);
        String sql = "SELECT ${(snake_keys + snake_fields)?join(',\\n')}\nFROM ${snake_case(entity.code)} " +
                where +
                order +
                limit;
        return this.jdbc.getJdbcTemplate().query(sql, this::extractData, param);
    }


    public List<${pascal_case(entity.name)}> query(String where, Object... param) {
        String sql = "SELECT ${(snake_keys + snake_fields)?join(',\\n')}\nFROM ${snake_case(entity.code)} " + where;
        return this.jdbc.getJdbcTemplate().query(sql, this::extractData, param);
    }

    public List<${pascal_case(entity.name)}> queryByPrimaryKey(Object key) {
        String sql = "SELECT ${(snake_keys + snake_fields)?join(',\\n')}\nFROM ${snake_case(entity.code)}  WHERE ${snake_keys?join('=? and \\n','','=? \\n')};";
        return this.jdbc.getJdbcTemplate().query(sql, this::extractData, key);
    }

    public int insert(${pascal_case(entity.name)}... entities) {
        String sql = "INSERT INTO ${snake_case(entity.code)} (${(snake_keys + snake_fields)?join(',\\n')}\n) VALUES (\n<#list 1..((snake_keys + snake_fields)?size - 1) as i>?,\n</#list>?\n)";
        int result = 0;
        for (var entity : entities) {
            <#list entity.fields as field>
            <#switch field.type>
            <#case 'String'>
            var ${camel_case(field.name)} = entity.get${pascal_case(field.name)}();
            <#break>
            <#case 'Integer'>
            var ${camel_case(field.name)} = entity.get${pascal_case(field.name)}();
            <#break>
            <#case 'Date'>
            var ${camel_case(field.name)} = entity.get${pascal_case(field.name)}();
            <#break>
            <#case 'Long'>
            var ${camel_case(field.name)} = entity.get${pascal_case(field.name)}();
            <#break>
            <#case 'Boolean'>
            var ${camel_case(field.name)} = entity.get${pascal_case(field.name)}();
            <#break>
            <#case 'Json'>
            var ${camel_case(field.name)} = entity.get${pascal_case(field.name)}();
            <#break>
            <#default>
            <#if field.type?ends_with("Type")>
            var ${camel_case(field.name)} = entity.get${pascal_case(field.name)}().getCode();
            <#else>
                // todo: ${field.type} 可能没有正确处理;
            </#if>
            </#switch>
            </#list>
            result += this.jdbc.getJdbcTemplate().update(sql, ${(camel_keys+camel_fields)?join(', ')});
        }
        return result;
    }

    public int update(${pascal_case(entity.name)}... entities) {

        String sql = "UPDATE ${snake_case(entity.code)} SET ${snake_fields?join('=?,\\n','','=?\\n')} WHERE ${snake_keys?join('=? and \\n','','=? \\n')};";
        int result = 0;
        for (var entity : entities) {
            <#list entity.fields as field>
            <#switch field.type>
            <#case 'String'>
            var ${camel_case(field.name)} = entity.get${pascal_case(field.name)}();
            <#break>
            <#case 'Integer'>
            var ${camel_case(field.name)} = entity.get${pascal_case(field.name)}();
            <#break>
            <#case 'Date'>
            var ${camel_case(field.name)} = entity.get${pascal_case(field.name)}();
            <#break>
            <#case 'Long'>
            var ${camel_case(field.name)} = entity.get${pascal_case(field.name)}();
            <#break>
            <#case 'Boolean'>
            var ${camel_case(field.name)} = entity.get${pascal_case(field.name)}();
            <#break>
            <#case 'Json'>
            var ${camel_case(field.name)} = entity.get${pascal_case(field.name)}();
            <#break>
            <#default>
            // todo: ${field.type} 没有被正确处理;
            var ${camel_case(field.name)} = entity.get${pascal_case(field.name)}().getCode();
            </#switch>
            </#list>

            result += this.jdbc.getJdbcTemplate().update(sql, ${(camel_fields + camel_keys)?join(', ')});
        }
        return result;
    }

    public int delete(int... ids) {
        String sql = "UPDATE ${snake_case(entity.code)} SET status = -1 WHERE ${snake_keys?join('=? and \\n','','=? \\n')};";
        int result = 0;
        for (var id : ids) {
            result += this.jdbc.getJdbcTemplate().update(sql, id);
        }
        return result;
    }

    public int deleteAll(Integer... ids) {
        String sql = "DELETE FROM ${snake_case(entity.code)} WHERE ${snake_keys?join('=? and \\n','','=? \\n')};";
        var list = Arrays.asList(ids);
        int result = 0;
        for (var partition : Lists.partition(list, 1000)) {
            result += Arrays.stream(this.jdbc.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setInt(1, partition.get(i));
                }

                @Override
                public int getBatchSize() {
                    return partition.size();
                }
            })).sum();
        }
        return result;
    }

    public <T> T sqlQuery(String sql, ResultSetExtractor<T> rse, @Nullable Object... args) {
        return this.jdbc.getJdbcTemplate().query(sql, rse, args);
    }

    /**
     * 返回分页对象
     *
     * @param sql      查询sql
     * @param sqlCount 总量sql
     * @param paramMap 查询参数
     * @param rse      mapper
     * @param index    当前页
     * @param size     每页大小
     * @param <T>      dto
     * @return 分页对象
     */
    public <T> Page<T> pageQuery(String sql, ResultSetExtractor<List<T>> rse, Map<String, Object> paramMap, String sqlCount, int index, int size) {
        paramMap.put("offset", index * size);
        paramMap.put("limit", size);
        final var sql_page = Objects.requireNonNull(local.getIfPresent(sql)).getValue(paramMap, String.class);
        final var sql_total = Objects.requireNonNull(local.getIfPresent(sqlCount)).getValue(paramMap, String.class);
        return this.jdbc.getJdbcTemplate().execute(new StatementCallback<Page<T>>() {
            @Override
            public Page<T> doInStatement(@Nullable Statement stmt) throws SQLException, DataAccessException {
                var page = new Page<T>();
                if (stmt == null) {
                    return page;
                }
                var cnt = stmt.executeQuery(sql_total);
                var total = 0L;
                if (cnt.next()) {
                    total = cnt.getLong(0);
                }
                if (total <= 0) {
                    return page;
                }
                var rs = stmt.executeQuery(sql_page);
                var data = rse.extractData(rs);
                page.setSize(size);
                page.setIndex(index);
                page.setTotal(total);
                page.setData(data);
                return page;
            }
        });
    }

    /**
     * 返回分页对象
     *
     * @param sql      查询sql
     * @param sqlCount 总量sql
     * @param paramMap 查询参数
     * @param rse      mapper
     * @param index    当前页
     * @param size     每页大小
     * @param <T>      dto
     * @return 分页对象
     */
    public <T> Page<T> pageQuery(String sql, String sqlCount, int index, int size,
                                 ResultSetExtractor<List<T>> rse, Map<String, ?> paramMap) {
        var count = this.jdbc.queryForObject(sqlCount, paramMap, Long.class);
        if (count == null || count <= 0) {
            return new Page<>();
        }
        var data = this.jdbc.query(sql, paramMap, rse);
        var page = new Page<T>();
        page.setSize(size);
        page.setIndex(index);
        page.setTotal(count);
        page.setData(data);
        return page;
    }

    public <T> T namedQuery(String sql, ResultSetExtractor<T> rse, Map<String, ?> paramMap) {
        return this.jdbc.query(sql, paramMap, rse);
    }

    public <T> T expressionQuery(String spel, ResultSetExtractor<T> rse, Map<String, ?> paramMap) {
        var expression = local.getIfPresent(spel);
        if (expression == null) {
            expression = this.parser.parseExpression(spel);
            local.put(spel, expression);
        }
        var sql = expression.getValue(paramMap, String.class);
        assert sql != null;
        return this.jdbc.query(sql, rse);
    }

    public List<${pascal_case(entity.name)}> extractData(ResultSet rs) {
        List<${pascal_case(entity.name)}> list = new ArrayList<>();
        try {
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
                <#case 'Long'>
                entity.set${pascal_case(field.name)}(rs.getLong("${snake_case(field.code)}"));
                <#break>
                <#case 'Boolean'>
                entity.set${pascal_case(field.name)}(rs.getBoolean("${snake_case(field.code)}"));
                <#break>
                <#case 'Json'>
                entity.set${pascal_case(field.name)}(new Json(rs.getString("${snake_case(field.code)}")));
                <#break>
                <#default>
                <#if field.type?ends_with("Type")>
                entity.set${pascal_case(field.name)}(${field.type}.valueOf(rs.getInt("${snake_case(field.code)}")));
                <#else>
                // todo: ${field.type} 可能没有正确处理;
                </#if>
                </#switch>
                </#list>
                list.add(entity);
            }
        } catch (SQLException e) {
            log.error("error", e);
        }
        return list;
    }
}
