package com.JamesCode.my_shopee;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyShopeeApplicationTests {

	@Test
	public void testMybatis() throws IOException {
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");

		//2.通過配置訊息取得一個SqlSessionFactory工廠對象
		SqlSessionFactory fac =	new SqlSessionFactoryBuilder().build( in );

		//3.通過工廠取得一個SqlSession對象
		SqlSession session = fac.openSession();

		//4.
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userid", "1");
		List<HashMap> list = session.selectList("com.JamesCode.my_shopee.mapper.EmpMapper.findAll_test",paramMap);

		//5.输出结果
		for (HashMap<String, Object> resultMap : list) {
			// Iterate through the keys (column names) in the HashMap
			for (String key : resultMap.keySet()) {
				// Get the value associated with the key
				Object value = resultMap.get(key);
				System.out.println("Column: " + key + ", Value: " + value);
			}
		}

	}

}
