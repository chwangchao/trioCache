package test.path.business;

import java.util.List;

public interface Face {

	UserBean getByid(int id);
	
	UserBean getByid_xml(int id);

	List<UserBean> getAll();
	
	List<UserBean> getAll_xml();
}
