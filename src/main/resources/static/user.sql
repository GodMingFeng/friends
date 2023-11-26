CREATE TABLE `user`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_uuid`    varchar(32)    NOT NULL DEFAULT '' COMMENT '用户uuid',
    `nick_name`    varchar(255)   NOT NULL DEFAULT '' COMMENT '用户昵称',
    `mobile`       varchar(64)    NOT NULL DEFAULT '' COMMENT '用户手机号',
    `avatar`       varchar(1023)  NOT NULL DEFAULT '' COMMENT '用户头像',
    `device_id`    varchar(128)   NOT NULL DEFAULT '' COMMENT '设备id',
    `lng`          decimal(10, 6) NOT NULL DEFAULT '0.000000' COMMENT '经度',
    `lat`          decimal(10, 6) NOT NULL DEFAULT '0.000000' COMMENT '维度',
    `ip`           varchar(64)    NOT NULL DEFAULT '' COMMENT '用户ip',
    `gmt_create`   datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_uuid` (`user_uuid`) USING BTREE,
    KEY            `idx_gmt_create` (`gmt_create`) USING BTREE,
    KEY            `idx_gmt_modified` (`gmt_modified`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';