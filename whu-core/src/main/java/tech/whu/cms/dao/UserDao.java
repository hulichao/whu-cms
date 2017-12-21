package tech.whu.cms.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import tech.whu.basic.dao.BaseDao;
import tech.whu.basic.model.Pager;
import tech.whu.cms.model.Group;
import tech.whu.cms.model.Role;
import tech.whu.cms.model.RoleType;
import tech.whu.cms.model.User;
import tech.whu.cms.model.UserGroup;





import tech.whu.cms.model.UserRole;

@SuppressWarnings("unchecked")
@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {

	@Override
	public List<Role> listUserRoles(int userId) {
		String hql = "select ur.role from UserRole ur where ur.user.id=?";
		return this.getSession().createQuery(hql).setParameter(0, userId).list();
	}

	@Override
	public List<Integer> listUserRoleIds(int userId) {
		String hql = "select ur.role.id from UserRole ur where ur.user.id=?";
		return this.getSession().createQuery(hql).setParameter(0, userId).list();
	}

	@Override
	public List<Group> listUserGroups(int userId) {
		String hql = "select ug.group from UserGroup ug where ug.user.id=?";
		return this.getSession().createQuery(hql).setParameter(0, userId).list();
	}

	@Override
	public List<Integer> listUserGroupIds(int userId) {
		String hql = "select ug.group.id from UserGroup ug where ug.user.id=?";
		return this.getSession().createQuery(hql).setParameter(0, userId).list();
	}

	@Override
	public UserRole loadUserRole(int userId, int roleId) {
		String hql = "select ur from UserRole ur left join fetch ur.user u left join fetch ur.role r where u.id=? and r.id=?";
		return (UserRole) this.getSession().createQuery(hql).setParameter(0, userId).setParameter(1, roleId)
				.uniqueResult();
	}

	@Override
	public UserGroup loadUserGroup(int userId, int groupId) {
		String hql = "select ug from UserGroup ug left join fetch ug.user u left join fetch ug.group g where u.id=? and g.id=?";
		return (UserGroup) this.getSession().createQuery(hql).setParameter(0, userId).setParameter(1, groupId)
				.uniqueResult();
	}

	@Override
	public User loadByUsername(String username) {
		String hql = "from User where username=?";
		return (User) this.queryObject(hql, username);
	}

	@Override
	public List<User> listRoleUsers(int roleId) {
		String hql = "select ur.user from UserRole ur where ur.role.id=?";
		return this.list(hql, roleId);
	}

	@Override
	public List<User> listRoleUsers(RoleType roleType) {
		String hql = "select ur.user from UserRole ur where ur.role.roleType=?";
		return this.list(hql, roleType);
	}

	@Override
	public List<User> listGroupUsers(int gid) {
		String hql = "select ug.user from UserGroup ug where ug.group.id=?";
		return this.list(hql, gid);
	}

	@Override
	public void addUserRole(User user, Role role) {
		UserRole ur = this.loadUserRole(user.getId(), role.getId());
		if (ur != null)
			return;
		ur = new UserRole();
		ur.setRole(role);
		ur.setUser(user);
		this.getSession().save(ur);
	}

	@Override
	public void addUserGroup(User user, Group group) {
		UserGroup ug = this.loadUserGroup(user.getId(), group.getId());
		if (ug != null)
			return;
		ug = new UserGroup();
		ug.setGroup(group);
		ug.setUser(user);
		this.getSession().save(ug);
	}

	@Override
	public void deleteUserRoles(int uid) {
		String hql = "delete UserRole ur where ur.user.id=?";
		this.updateByHql(hql, uid);
	}

	@Override
	public void deleteUserGroups(int gid) {
		String hql = "delete UserGroup ug where ug.user.id=?";
		this.updateByHql(hql, gid);
	}

	@Override
	public Pager<User> findUser() {
		return findUser(null, null);
	}

	@Override
	public Pager<User> findUser(String searchCode, String SearchValue) {
		return findUser(null, null, searchCode, SearchValue);
	}

	@Override
	public Pager<User> findUser(String gId, String rId, String searchCode, String searchValue) {
		String hql = "from User u where 1=1";
		if (StringUtils.isNotBlank(searchCode) && StringUtils.isNotBlank(searchValue)) {
			if ("id".equals(searchCode)) {
				hql += " and u.id like '%" + searchValue + "%'";
			} else if ("username".equals(searchCode)) {
				hql += " and u.username like '%" + searchValue + "%'";
			} else if ("nickname".equals(searchCode)) {
				hql += " and u.nickname like '%" + searchValue + "%'";
			}
		}
		if (gId != null && !"".equals(gId)) {
			hql += "and u.id in (select ug.user.id from UserGroup ug where ug.group.id=" + gId + ")";
		}
		if (rId != null && !"".equals(rId)) {
			hql += "and u.id in (select ur.user.id from UserRole ur where ur.role.id=" + rId + ")";
		}
		
		return this.find(hql);
	}

	@Override
	public void deleteUserRole(int uid, int rid) {
		String hql = "delete UserRole ur where ur.user.id=? and ur.role.id=?";
		this.updateByHql(hql, new Object[] { uid, rid });
	}

	@Override
	public void deleteUserGroup(int uid, int gid) {
		String hql = "delete UserGroup ug where ug.user.id=? and ug.group.id=?";
		this.updateByHql(hql, new Object[] { uid, gid });
	}

}
