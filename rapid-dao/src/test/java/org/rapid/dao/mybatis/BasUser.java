package org.rapid.dao.mybatis;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.rapid.util.common.model.Unique;

@Table(name = "bas_user")
public class BasUser implements Unique<Integer> {
	
	private static final long serialVersionUID = -7964551589123377482L;

	/**
     * 主键
     */
    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    /**
     * 密码	
     */
    private String password;
    
    /**
     * 交易密码	
     */
    private String bizpwd;

	/**
     * 密码校验 	password+salt
     */
    private String salt;

    /**
     * 状态	0-不启用 1-启用
     */
    private Integer status;

    /**
     * 电话	
     */
    private String mobile;

    /**
     * 邮箱	
     */
    private String email;

    /**
     * 类型	0-个人 1-公司
     */
    private Integer type;

    /**
     * 上次登录ip	
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 上次登录时间	
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    private String referer;

    /**
     * 用户平台客户号
     */
    @Column(name = "platform_id")
    private String platformId;
    /**
     * 资产方ID
     */
    @Column(name = "asset_id")
    private Integer assetId;

    /**
     * 添加时间	
     */
    private Date addtime;

    /**
     * 添加ip
     */
    private String addip;
    
    private Integer migrate;
    
    private Integer modVal;
    private String clientId;
    

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码	
     *
     * @return password - 密码	
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码	
     *
     * @param password 密码	
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getBizpwd() {
		return bizpwd;
	}

	public void setBizpwd(String bizpwd) {
		this.bizpwd = bizpwd;
	}

    /**
     * 获取密码校验 	password+salt
     *
     * @return salt - 密码校验 	password+salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置密码校验 	password+salt
     *
     * @param salt 密码校验 	password+salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取状态	0-不启用 1-启用
     *
     * @return status - 状态	0-不启用 1-启用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态	0-不启用 1-启用
     *
     * @param status 状态	0-不启用 1-启用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取电话	
     *
     * @return mobile - 电话	
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置电话	
     *
     * @param mobile 电话	
     */
    public BasUser setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    /**
     * 获取邮箱	
     *
     * @return email - 邮箱	
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱	
     *
     * @param email 邮箱	
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取类型	
     *
     * @return type - 类型	
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型	
     *
     * @param type 类型	
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取上次登录ip	
     *
     * @return last_login_ip - 上次登录ip	
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 设置上次登录ip	
     *
     * @param lastLoginIp 上次登录ip	
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * 获取上次登录时间	
     *
     * @return last_login_time - 上次登录时间	
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置上次登录时间	
     *
     * @param lastLoginTime 上次登录时间	
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * @return referer
     */
    public String getReferer() {
        return referer;
    }

    /**
     * @param referer
     */
    public void setReferer(String referer) {
        this.referer = referer;
    }

    /**
     * 获取添加时间	
     *
     * @return addtime - 添加时间	
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置添加时间	
     *
     * @param addtime 添加时间	
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取添加ip
     *
     * @return addip - 添加ip
     */
    public String getAddip() {
        return addip;
    }

    /**
     * 设置添加ip
     *
     * @param addip 添加ip
     */
    public void setAddip(String addip) {
        this.addip = addip;
    }

    /**
     * 获取 用户平台客户号
     */
    public String getPlatformId() {
        return this.platformId;
    }

    /**
     * 设置 用户平台客户号
     */
    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }
    
    public Integer getMigrate() {
		return migrate;
	}
    
    public void setMigrate(Integer migrate) {
		this.migrate = migrate;
	}
    
    public String getClientId() {
		return clientId;
	}
    
    public void setClientId(String clientId) {
		this.clientId = clientId;
	}
    
    public Integer getModVal() {
		return modVal;
	}
    
    public void setModVal(Integer modVal) {
		this.modVal = modVal;
	}

	@Override
	public Integer id() {
		return this.id;
	}
}