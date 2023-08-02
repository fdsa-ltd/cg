package ltd.fdsa.client;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ltd.fdsa.client.service.DatasetService;
import ltd.fdsa.client.entity.Dataset;

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
public class TestDatasetService {

    @Autowired
    DatasetService service;


    /***
    * 查询所有
    */
    @Test
    public void TestDatasetQuery() {
        long count = this.service.findAll("").stream().count();
        Assert.assertTrue("查询到数据", count > 0);
    }
 
    @Test
    public void TestInsertDataset() {
        Dataset entity = new Dataset();
        entity.setFileId("test");
        entity.setUserId("test");
        entity.setPath("test");
        entity.setType("test");
        // Long ;
        entity.setWidth(555);
        entity.setHeight(555);
        entity.setTags("test");
        entity.setCid(555);
        // Byte ;
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
//    public void TestEditDataset() {

//        boolean flag = this.service.saveOrUpdateDataset(DatasetSaveReq);
//        Assert.assertTrue(flag);
//    }


//    /***
//    * 删除
//    */
//    @Test
//    @Rollback
//    @Transactional
//    public void TestDeleteDataset() {

////boolean flag = this.service.deleteDataset(DatasetDelReq);

//        Assert.assertTrue(flag);
//    }



//    /***
//    * 查询所有
//    */
//    @Test
//    public void TestSelect() {
//        List<Dataset> DatasetList = this.service.list(null);
//        if (DatasetList != null) {
//            DatasetList.forEach(System.out::println);
//        }
//    }
}
