package com.example.friends.mapper.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "favorites")
public class Favorites implements Serializable {

    private static final long serialVersionUID = 3269784291037747685L;

    /**
     * 自增id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 状态
     * 0:启用
     * 1:禁用
     */
    private Integer status;

    /**
     * 用户id
     */
    private String openId;

    /**
     * 收藏项id
     */
    private String itemId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
