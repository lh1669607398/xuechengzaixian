package com.xuecheng.mange_cms;

import com.xuecheng.manage_cms.ManageCmsApplication;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


@SpringBootTest(classes = ManageCmsApplication.class)
@RunWith(SpringRunner.class)
public class GridFsTemplateTest {
    @Autowired
    private GridFsTemplate gridFsTemplate;

    /**
     * 存入文件
     * @throws FileNotFoundException
     */
    @Test
    public void testGridFs() throws FileNotFoundException {
        //获取文件
        File file= new File("D:\\index_banner.ftl");
        //读取文件
        FileInputStream inputStream= new FileInputStream(file);
        //向GridFs中存储文件
        ObjectId store = gridFsTemplate.store(inputStream, "轮播路测试文件01");
        //得到文件ID
        String s = store.toString();
        System.out.println(s);
    }


}
