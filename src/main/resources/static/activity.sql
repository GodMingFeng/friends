CREATE TABLE `activity`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title`        varchar(255)   NOT NULL DEFAULT '' COMMENT '活动标题',
    `description`  varchar(1023)  NOT NULL DEFAULT '' COMMENT '活动描述',
    `start_time`   datetime       NOT NULL COMMENT '活动开始时间',
    `end_time`     datetime       NOT NULL COMMENT '活动结束时间',
    `images`       text COMMENT '图片链接',
    `open_hours`   varchar(255)   NOT NULL DEFAULT '' COMMENT '营业时间',
    `comments`     text COMMENT '活动说明',
    `content`      text COMMENT '活动内容',
    `province`     varchar(64)    NOT NULL DEFAULT '' COMMENT '省',
    `province_id`  int(4) NOT NULL DEFAULT '0' COMMENT '省id',
    `city`         varchar(64)    NOT NULL DEFAULT '' COMMENT '市',
    `city_id`      int(4) NOT NULL DEFAULT '0' COMMENT '市id',
    `district`     varchar(64)    NOT NULL DEFAULT '' COMMENT '区',
    `district_id`  int(4) NOT NULL DEFAULT '0' COMMENT '区id',
    `address`      varchar(1023)  NOT NULL DEFAULT '' COMMENT '活动地址',
    `lng`          decimal(10, 6) NOT NULL DEFAULT '0.000000' COMMENT '经度',
    `lat`          decimal(10, 6) NOT NULL DEFAULT '0.000000' COMMENT '维度',
    `apply_id`     bigint(20) NOT NULL DEFAULT '0' COMMENT '申请id',
    `gmt_create`   datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `store_info`   text COMMENT '商户信息',
    `store_name`   varchar(255)   NOT NULL COMMENT '商户名称',
    PRIMARY KEY (`id`),
    KEY            `idx_gmt_create` (`gmt_create`) USING BTREE,
    KEY            `idx_gmt_modified` (`gmt_modified`) USING BTREE,
    KEY            `idx_start_end` (`start_time`,`end_time`) USING BTREE,
    KEY            `idx_apply_id` (`apply_id`) USING BTREE,
    KEY            `idx_province_city_district` (`province_id`,`city_id`,`district_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='活动表';