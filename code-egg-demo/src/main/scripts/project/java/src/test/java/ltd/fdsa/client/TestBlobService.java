package ltd.fdsa.client;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ltd.fdsa.client.service.BlobService;
import ltd.fdsa.client.entity.Blob;

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
public class TestBlobService {

    @Autowired
    BlobService service;


    /***
    * 查询所有
    */
    @Test
    public void TestBlobQuery() {
        long count = this.service.findAll("").stream().count();
        Assert.assertTrue("查询到数据", count > 0);
    }
 
    @Test
    public void TestInsertBlob() {
        Blob entity = new Blob();
        entity.setFileId("test");
        entity.setType("test");
        // Long ;
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
//    public void TestEditBlob() {

//        boolean flag = this.service.saveOrUpdateBlob(BlobSaveReq);
//        Assert.assertTrue(flag);
//    }


//    /***
//    * 删除
//    */
//    @Test
//    @Rollback
//    @Transactional
//    public void TestDeleteBlob() {

////boolean flag = this.service.deleteBlob(BlobDelReq);

//        Assert.assertTrue(flag);
//    }



//    /***
//    * 查询所有
//    */
//    @Test
//    public void TestSelect() {
//        List<Blob> BlobList = this.service.list(null);
//        if (BlobList != null) {
//            BlobList.forEach(System.out::println);
//        }
//    }
}
