CREATE TABLE `message`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `content`      varchar(1024) NOT NULL DEFAULT '' COMMENT '漂流瓶的内容',
    `user_uuid`    varchar(32)   NOT NULL DEFAULT '' COMMENT '扔出漂流瓶的用户',
    `status`       int(4) unsigned NOT NULL DEFAULT '0' COMMENT '漂流瓶状态',
    `lng`          decimal(10, 8)         DEFAULT NULL COMMENT '漂流瓶的经度',
    `lat`          decimal(10, 8)         DEFAULT NULL COMMENT '漂流瓶的纬度',
    `gmt_create`   datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) COMMENT '创建时间',
    `gmt_modified` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY            `idx_gmt_create` (`gmt_create`) USING BTREE,
    KEY            `idx_gmt_modified` (`gmt_modified`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;