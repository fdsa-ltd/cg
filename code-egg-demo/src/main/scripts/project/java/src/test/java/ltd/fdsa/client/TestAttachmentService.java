package ltd.fdsa.client;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ltd.fdsa.client.service.AttachmentService;
import ltd.fdsa.client.entity.Attachment;

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
public class TestAttachmentService {

    @Autowired
    AttachmentService service;


    /***
    * 查询所有
    */
    @Test
    public void TestAttachmentQuery() {
        long count = this.service.findAll("").stream().count();
        Assert.assertTrue("查询到数据", count > 0);
    }
 
    @Test
    public void TestInsertAttachment() {
        Attachment entity = new Attachment();
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
//    public void TestEditAttachment() {

//        boolean flag = this.service.saveOrUpdateAttachment(AttachmentSaveReq);
//        Assert.assertTrue(flag);
//    }


//    /***
//    * 删除
//    */
//    @Test
//    @Rollback
//    @Transactional
//    public void TestDeleteAttachment() {

////boolean flag = this.service.deleteAttachment(AttachmentDelReq);

//        Assert.assertTrue(flag);
//    }



//    /***
//    * 查询所有
//    */
//    @Test
//    public void TestSelect() {
//        List<Attachment> AttachmentList = this.service.list(null);
//        if (AttachmentList != null) {
//            AttachmentList.forEach(System.out::println);
//        }
//    }
}
