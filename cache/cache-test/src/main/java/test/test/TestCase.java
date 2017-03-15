package test.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.path.business.Face;
import test.path.business.UserBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestCase {

	@Resource
	Face face;


	@Test
	public void test_getByid() throws Exception {
		UserBean userBean = face.getByid(1);
		System.out.println(userBean);
		userBean = face.getByid(1);
		System.out.println(userBean);
		System.out.println(userBean.getClass());
	}

	@Test
	public void test_getAll() throws Exception {
		List<UserBean> userBean = face.getAll();
		System.out.println(userBean);
		userBean = face.getAll();
		
		System.out.println(userBean.get(0).getClass());
	}
}
