package ltd.fdsa.client;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ltd.fdsa.client.service.UserFileService;
import ltd.fdsa.client.entity.UserFile;

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
public class TestUserFileService {

    @Autowired
    UserFileService service;


    /***
    * 查询所有
    */
    @Test
    public void TestUserFileQuery() {
        long count = this.service.findAll("").stream().count();
        Assert.assertTrue("查询到数据", count > 0);
    }
 
    @Test
    public void TestInsertUserFile() {
        UserFile entity = new UserFile();
        entity.setFileId("test");
        entity.setBlobId("test");
        entity.setUserId("test");
        entity.setPath("test");
        entity.setType("test");
        // Long ;
        entity.setWidth(555);
        entity.setHeight(555);
        entity.setTags("test");
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
//    public void TestEditUserFile() {

//        boolean flag = this.service.saveOrUpdateUserFile(UserFileSaveReq);
//        Assert.assertTrue(flag);
//    }


//    /***
//    * 删除
//    */
//    @Test
//    @Rollback
//    @Transactional
//    public void TestDeleteUserFile() {

////boolean flag = this.service.deleteUserFile(UserFileDelReq);

//        Assert.assertTrue(flag);
//    }



//    /***
//    * 查询所有
//    */
//    @Test
//    public void TestSelect() {
//        List<UserFile> UserFileList = this.service.list(null);
//        if (UserFileList != null) {
//            UserFileList.forEach(System.out::println);
//        }
//    }
}
