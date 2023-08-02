package ltd.fdsa.client;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ltd.fdsa.client.service.AzomeService;
import ltd.fdsa.client.entity.Azome;

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
public class TestAzomeService {

    @Autowired
    AzomeService service;


    /***
    * 查询所有
    */
    @Test
    public void TestAzomeQuery() {
        long count = this.service.findAll("").stream().count();
        Assert.assertTrue("查询到数据", count > 0);
    }
 
    @Test
    public void TestInsertAzome() {
        Azome entity = new Azome();
        entity.setFileId(555);
        entity.setUsernm("test");
        entity.setMobilePhone("test");
        entity.setEmailAddress("test");
        entity.setPassword("test");
        entity.setFromTime("test");
        entity.setEndTime("test");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setCreateBy(555);
        entity.setUpdateBy(555);
        int count = this.service.insert(entity);
        Assert.assertTrue("插件数据", count > 0);
    }


//        /***
//        * 保存
//        */
//    @Test
//    @Rollback
//    @Transactional
//    public void TestEditAzome() {

//        boolean flag = this.service.saveOrUpdateAzome(AzomeSaveReq);
//        Assert.assertTrue(flag);
//    }


//    /***
//    * 删除
//    */
//    @Test
//    @Rollback
//    @Transactional
//    public void TestDeleteAzome() {

////boolean flag = this.service.deleteAzome(AzomeDelReq);

//        Assert.assertTrue(flag);
//    }



//    /***
//    * 查询所有
//    */
//    @Test
//    public void TestSelect() {
//        List<Azome> AzomeList = this.service.list(null);
//        if (AzomeList != null) {
//            AzomeList.forEach(System.out::println);
//        }
//    }
}
