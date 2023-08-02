package ${setting('project.package','ltd.fdsa.client')};

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ${setting('project.package','ltd.fdsa.client')}.service.${pascal_case(entity.name)}Service;
import ${setting('project.package','ltd.fdsa.client')}.entity.${pascal_case(entity.name)};

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
/**
*
* @ClassName:
* @description:
* @author zhumingwu
* @since 2020-12-09
**/
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {ClientApplication.class})
@Slf4j
public class Test${pascal_case(entity.name)}Service {

    @Autowired
    ${pascal_case(entity.name)}Service service;


    /***
    * 查询所有
    */
    @Test
    public void Test${pascal_case(entity.name)}Query() {
        long count = this.service.findAll("").stream().count();
        Assert.assertTrue("查询到数据", count > 0);
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
        <#default>
        // ${field.type} ;
        </#switch>
        </#list>
        int count = this.service.insert(entity);
        Assert.assertTrue("插件数据", count > 0);
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
