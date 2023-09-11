package ${setting('project.package','//todo add project.package')?replace('/','.')};

import lombok.extern.slf4j.Slf4j;
import ${setting('project.package','//todo add project.package')?replace('/','.')}.service.${pascal_case(entity.name)}Service;
import ${setting('project.package','//todo add project.package')?replace('/','.')}.entity.${pascal_case(entity.name)};
import ${setting('project.package','//todo add project.package')?replace('/','.')}.enums.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * @author zhumingwu
 * @ClassName:
 * @description:
 * @since 2020-12-09
 **/
@SpringBootTest
@ContextConfiguration(classes = {${setting('project.name')}Application.class})
@Slf4j
public class ${pascal_case(entity.name)}ServiceTests {

    @Autowired
    ${pascal_case(entity.name)}Service service;

    /***
     * 查询所有
     */
    @Test
    public void Test${pascal_case(entity.name)}Query() {
        long count = this.service.findAll("").size();
        Assert.isTrue(count > 0, "查询到数据");
    }

    @Test
    public void TestInsert${pascal_case(entity.name)}() {
        ${pascal_case(entity.name)} entity = new ${pascal_case(entity.name)}();
        <#list entity.fields as field>        
        <#switch field.type>
        <#case 'String'>
        entity.set${pascal_case(field.name)}("test");
        <#break>
        <#case 'Integer'>
        entity.set${pascal_case(field.name)}(555);
        <#break>
        <#case 'Date'>
        entity.set${pascal_case(field.name)}(new Date());
        <#break>
        <#case 'Long'>
        entity.set${pascal_case(field.name)}(555L);
        <#break>
        <#default>
        <#if field.type?ends_with("Type")>
        entity.set${pascal_case(field.name)}(${field.type}.valueOf(1));
        <#else>
        // todo: ${field.type} 可能没有正确处理;
        </#if>
        </#switch>
        </#list>
        int count = this.service.insert(entity);
        Assert.isTrue(count > 0, "插件数据");
    }

//        /***
//        * 保存
//        */
//    @Test
//    @Rollback
//    @Transactional
//    public void TestEdit${pascal_case(entity.name)}() {

//        boolean flag = this.service.saveOrUpdate${pascal_case(entity.name)}(${pascal_case(entity.name)}SaveReq);
//        Assert.assertTrue(flag);
//    }

//    /***
//    * 删除
//    */
//    @Test
//    @Rollback
//    @Transactional
//    public void TestDelete${pascal_case(entity.name)}() {

////boolean flag = this.service.delete${pascal_case(entity.name)}(${pascal_case(entity.name)}DelReq);

//        Assert.assertTrue(flag);
//    }


//    /***
//    * 查询所有
//    */
//    @Test
//    public void TestSelect() {
//        List<${pascal_case(entity.name)}> ${pascal_case(entity.name)}List = this.service.list(null);
//        if (${pascal_case(entity.name)}List != null) {
//            ${pascal_case(entity.name)}List.forEach(System.out::println);
//        }
//    }
}
