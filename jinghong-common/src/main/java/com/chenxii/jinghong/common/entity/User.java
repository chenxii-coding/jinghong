package com.chenxii.jinghong.common.entity;

import com.chenxii.jinghong.common.config.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName user
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "user")
@Data
public class User extends BaseEntity implements Serializable {

    private String id;

    private String username;

    private String password;

    private String sex;

    private String birthday;

    private Date lastLoginTime;

    private String status;

    private String token;

    private static final long serialVersionUID = 1L;

}
