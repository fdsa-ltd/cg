package ltd.fdsa.client;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ltd.fdsa.client.service.UserService;
import ltd.fdsa.client.entity.User;

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
public class TestUserService {

    @Autowired
    UserService service;


    /***
    * 查询所有
    */
    @Test
    public void TestUserQuery() {
        long count = this.service.findAll("").stream().count();
        Assert.assertTrue("查询到数据", count > 0);
    }
 
    @Test
    public void TestInsertUser() {
        User entity = new User();
        entity.setId(555);
        entity.setUsernm("test");
        entity.setMobilePhone("test");
        entity.setEmailAddress("test");
        entity.setPassword("test");
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
//    public void TestEditUser() {

//        boolean flag = this.service.saveOrUpdateUser(UserSaveReq);
//        Assert.assertTrue(flag);
//    }


//    /***
//    * 删除
//    */
//    @Test
//    @Rollback
//    @Transactional
//    public void TestDeleteUser() {

////boolean flag = this.service.deleteUser(UserDelReq);

//        Assert.assertTrue(flag);
//    }



//    /***
//    * 查询所有
//    */
//    @Test
//    public void TestSelect() {
//        List<User> UserList = this.service.list(null);
//        if (UserList != null) {
//            UserList.forEach(System.out::println);
//        }
//    }
}
