package com.example.friends.mapper.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "message")
public class Message implements Serializable {

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
    private String content;

    /**
     * 用户唯一标识
     */
    private String userUniqueId;

    /**
     * 唯一id
     */
    private String uniqueId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 经度
     */
    private BigDecimal lng;

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
