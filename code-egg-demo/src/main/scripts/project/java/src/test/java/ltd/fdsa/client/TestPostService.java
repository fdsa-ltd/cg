package ltd.fdsa.client;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import ltd.fdsa.client.service.PostService;
import ltd.fdsa.client.entity.Post;

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
public class TestPostService {

    @Autowired
    PostService service;


    /***
    * 查询所有
    */
    @Test
    public void TestPostQuery() {
        long count = this.service.findAll("").stream().count();
        Assert.assertTrue("查询到数据", count > 0);
    }
 
    @Test
    public void TestInsertPost() {
        Post entity = new Post();
        entity.setId(555);
        entity.setTitle("test");
        entity.setContent("test");
        entity.setImage("test");
        entity.setGeometry("test");
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
//    public void TestEditPost() {

//        boolean flag = this.service.saveOrUpdatePost(PostSaveReq);
//        Assert.assertTrue(flag);
//    }


//    /***
//    * 删除
//    */
//    @Test
//    @Rollback
//    @Transactional
//    public void TestDeletePost() {

////boolean flag = this.service.deletePost(PostDelReq);

//        Assert.assertTrue(flag);
//    }



//    /***
//    * 查询所有
//    */
//    @Test
//    public void TestSelect() {
//        List<Post> PostList = this.service.list(null);
//        if (PostList != null) {
//            PostList.forEach(System.out::println);
//        }
//    }
}
