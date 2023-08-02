package ltd.fdsa.client;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ltd.fdsa.client.service.UserRoleService;
import ltd.fdsa.client.entity.UserRole;

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
public class TestUserRoleService {

    @Autowired
    UserRoleService service;


    /***
    * 查询所有
    */
    @Test
    public void TestUserRoleQuery() {
        long count = this.service.findAll("").stream().count();
        Assert.assertTrue("查询到数据", count > 0);
    }
 
    @Test
    public void TestInsertUserRole() {
        UserRole entity = new UserRole();
        entity.setId(555);
        entity.setUserId(555);
        entity.setRoleId(555);
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
//    public void TestEditUserRole() {

//        boolean flag = this.service.saveOrUpdateUserRole(UserRoleSaveReq);
//        Assert.assertTrue(flag);
//    }


//    /***
//    * 删除
//    */
//    @Test
//    @Rollback
//    @Transactional
//    public void TestDeleteUserRole() {

////boolean flag = this.service.deleteUserRole(UserRoleDelReq);

//        Assert.assertTrue(flag);
//    }



//    /***
//    * 查询所有
//    */
//    @Test
//    public void TestSelect() {
//        List<UserRole> UserRoleList = this.service.list(null);
//        if (UserRoleList != null) {
//            UserRoleList.forEach(System.out::println);
//        }
//    }
}
