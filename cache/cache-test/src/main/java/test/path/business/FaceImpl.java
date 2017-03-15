package test.path.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.zh.cn.trio.cache.annotations.TrioCache;
import com.zh.cn.trio.cache.strategy.model.CacheModel;

public class FaceImpl implements Face {

	private static final List<UserBean> LIST = new ArrayList<>();
	static {
		UserBean u1 = new UserBean(1, "W1");
		UserBean u2 = new UserBean(2, "W2");
		UserBean u3 = new UserBean(3, "z3");
		List<UserBean> beans = Arrays.asList(u1, u2, u3);
		for (UserBean userBean : beans) {
			LIST.add(userBean);
		}
	}

	@TrioCache(cacheTime=10000)
	@Override
	public UserBean getByid(int id) {
		System.out.println("getByid");
		return LIST.get(id);
	}
	
	@Override
	public UserBean getByid_xml(int id) {
		System.out.println("getByid_xml");
		return LIST.get(id);
	}
	

	@TrioCache(cacheTime=10000,cacheModel=CacheModel.READ_WRITE_FLUSH)
	@Override
	public List<UserBean> getAll() {
		System.out.println("getAll");
		return LIST;
	}

	@Override
	public List<UserBean> getAll_xml() {
		System.out.println("getAll_xml");
		return LIST;
	}
	
}
