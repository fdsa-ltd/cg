package ltd.fdsa.client;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ltd.fdsa.client.service.UserLoginService;
import ltd.fdsa.client.entity.UserLogin;

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
public class TestUserLoginService {

    @Autowired
    UserLoginService service;


    /***
    * 查询所有
    */
    @Test
    public void TestUserLoginQuery() {
        long count = this.service.findAll("").stream().count();
        Assert.assertTrue("查询到数据", count > 0);
    }
 
    @Test
    public void TestInsertUserLogin() {
        UserLogin entity = new UserLogin();
        entity.setId(555);
        entity.setUserId(555);
        entity.setProvider("test");
        entity.setKey("test");
        entity.setToken("test");
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
//    public void TestEditUserLogin() {

//        boolean flag = this.service.saveOrUpdateUserLogin(UserLoginSaveReq);
//        Assert.assertTrue(flag);
//    }


//    /***
//    * 删除
//    */
//    @Test
//    @Rollback
//    @Transactional
//    public void TestDeleteUserLogin() {

////boolean flag = this.service.deleteUserLogin(UserLoginDelReq);

//        Assert.assertTrue(flag);
//    }



//    /***
//    * 查询所有
//    */
//    @Test
//    public void TestSelect() {
//        List<UserLogin> UserLoginList = this.service.list(null);
//        if (UserLoginList != null) {
//            UserLoginList.forEach(System.out::println);
//        }
//    }
}
