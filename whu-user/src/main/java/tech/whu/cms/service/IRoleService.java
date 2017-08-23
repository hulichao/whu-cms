package tech.whu.cms.service;

import java.util.List;

import tech.whu.basic.model.Pager;
import tech.whu.cms.model.Role;
import tech.whu.cms.model.RoleFindModel;

public interface IRoleService {
	public void add(Role role);
	public void delete(int id);
	public void update(Role role);
	public Role load(int id);
	public List<Role> listRole();





	public Pager<RoleFindModel> findRole();
	public void deleteRoleUsers(int rid);
}
