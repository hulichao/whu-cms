package tech.whu.cms.service;

import java.util.List;

import tech.whu.basic.model.Pager;
import tech.whu.cms.model.ChannelTree;
import tech.whu.cms.model.Group;
import tech.whu.cms.model.GroupChannel;
import tech.whu.cms.model.GroupFindModel;

public interface IGroupService {
	public void add(Group group);
	public void delete(int id);
	public Group load(int id);
	public void update(Group group);
	
	public List<Group> listGroup();
	public Pager<GroupFindModel> findGroup();
	public void deleteGroupUsers(int gid);
	
	/**
	 * 添加GroupChannel对象
	 * @param gid
	 * @param cid
	 */
	public void addGroupChannel(int gid,int cid);
	/**
	 * 加载GroupChannel对象
	 * @param gid
	 * @param cid
	 * @return
	 */
	public GroupChannel loadGroupChannel(int gid,int cid);
	/**
	 * 清空组所管理的栏目
	 * @param gid
	 */
	public void clearGroupChannel(int gid);
	/**
	 * 删除用户栏目
	 * @param gid
	 * @param cid
	 */
	public void deleteGroupChannel(int gid,int cid);
	/**
	 * 获取某个组的所有管理栏目的id
	 * @param gid
	 * @return
	 */
	public List<Integer> listGroupChannelIds(int gid);
	/**
	 * 获取某个组的栏目树
	 * @param gid
	 * @return
	 */
	public List<ChannelTree> generateGroupChannelTree(int gid);
	/**
	 * 获取某个用户的栏目树
	 * @param uid
	 * @return
	 */
	public List<ChannelTree> generateUserChannelTree(int uid);
}
