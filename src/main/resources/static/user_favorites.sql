CREATE TABLE `user_favorites`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `type`         int(4) NOT NULL DEFAULT 0 COMMENT '收藏类型',
    `item_id`      varchar(128) NOT NULL DEFAULT '' COMMENT '收藏id',
    `open_id`      varchar(128) NOT NULL DEFAULT '' COMMENT 'open_id',
    `status`       int(4) NOT NULL DEFAULT 0 COMMENT '状态',
    `gmt_create`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_open_id_type_item_id` (`open_id`,`type`,`item_id`) USING BTREE,
    KEY            `idx_gmt_create` (`gmt_create`) USING BTREE,
    KEY            `idx_gmt_modified` (`gmt_modified`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户收藏表';