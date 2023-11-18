CREATE TABLE `user`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `nick_name`    varchar(255)  NOT NULL DEFAULT '' COMMENT '用户昵称',
    `mobile`       varchar(64)   NOT NULL DEFAULT '' COMMENT '用户手机号',
    `avatar`       varchar(1023) NOT NULL DEFAULT '' COMMENT '用户头像',
    `session_key`  varchar(128)  NOT NULL DEFAULT '' COMMENT '会话key',
    `union_id`     varchar(128)  NOT NULL DEFAULT '' COMMENT 'union_id',
    `open_id`      varchar(128)  NOT NULL DEFAULT '' COMMENT 'open_id',
    `access_token` varchar(128)  NOT NULL COMMENT 'access_token',
    `gmt_create`   datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_open_id` (`open_id`) USING BTREE,
    KEY            `idx_gmt_create` (`gmt_create`) USING BTREE,
    KEY            `idx_gmt_modified` (`gmt_modified`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';