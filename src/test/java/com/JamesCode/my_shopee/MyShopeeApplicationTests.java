package com.JamesCode.my_shopee;

import com.JamesCode.my_shopee.pojo.Cart;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
public class MyShopeeApplicationTests {

	@Test
	public void testMybatis() throws IOException {
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");

		//2.通过配置信息获取一个SqlSessionFactory工厂对象
		SqlSessionFactory fac =	new SqlSessionFactoryBuilder().build( in );

		//3.通过工厂获取一个SqlSession对象
		SqlSession session = fac.openSession();

		//4.通过namespace+id找到要执行的sql语句并执行sql语句
		List<Cart> list = session.selectList("EmpMapper.findAll_test");

		//5.输出结果
		for(Cart e : list) {
			System.out.println( e );
		}
	}

}
