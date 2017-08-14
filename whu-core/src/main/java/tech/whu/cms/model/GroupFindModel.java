package tech.whu.cms.model;

import java.math.BigInteger;

/**
 * Created by cjbi on 2017/9/9.
 */
public class GroupFindModel {

    /**
     * 组id
     */
    private int id;

    /**
     * 组名称
     */
    private String name;
    /**
     * 组描述信息
     */
    private String descr;

    /**
     * 用户数量
     */
    private BigInteger userCount;

    public BigInteger getUserCount() {
        return userCount;
    }

    public void setUserCount(BigInteger userCount) {
        this.userCount = userCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
