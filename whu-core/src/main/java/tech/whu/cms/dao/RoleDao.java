package tech.whu.cms.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import tech.whu.basic.dao.BaseDao;
import tech.whu.basic.model.Pager;
import tech.whu.cms.model.Role;
import tech.whu.cms.model.RoleFindModel;

@Repository("roleDao")
public class RoleDao extends BaseDao<Role> implements IRoleDao {

	@Override
	public List<Role> listRole() {
		return this.list("from Role");
	}
	
	@Override
	public void deleteRoleUsers(int rid) {
		this.updateByHql("delete UserRole ur where ur.role.id=?",rid);
	}

	@Override
	public Pager<RoleFindModel> findRole() {
		String sql = "select * from (select a.id,a.name,a.role_type as roleType,(select count(id) from t_user_role b where a.id=b.r_id) as userCount from t_role a) as t";
		return this.findBySql(sql, RoleFindModel.class,false);
	}


}
