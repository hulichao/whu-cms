package tech.whu.cms.dao;

import java.util.List;

import tech.whu.basic.dao.IBaseDao;
import tech.whu.basic.model.Pager;
import tech.whu.cms.model.Role;
import tech.whu.cms.model.RoleFindModel;

public interface IRoleDao extends IBaseDao<Role> {
	public List<Role> listRole();
	public Pager<RoleFindModel> findRole();
	public void deleteRoleUsers(int rid);
}
