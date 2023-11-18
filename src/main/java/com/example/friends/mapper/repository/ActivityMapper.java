package com.example.friends.mapper.repository;

import com.example.friends.common.enums.ActivityType;
import com.example.friends.common.utils.DateUtils;
import com.example.friends.mapper.condition.ActivityCondition;
import com.example.friends.mapper.domain.Activity;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

public interface ActivityMapper extends Mapper<Activity>, MySqlMapper<Activity> {

    default List<Activity> selectByCondition(ActivityCondition activityCondition) {
        var example = new Example(Activity.class);
        example.createCriteria();
        //关键词
        if (!StringUtils.isEmpty(activityCondition.getKeywords())) {
            example.and().orLike("title", "%" + activityCondition.getKeywords() + "%")
                    .orLike("description", "%" + activityCondition.getKeywords() + "%");
        }
        var now = new Date();
        //类型
        if (activityCondition.getType() != null) {
            var type = ActivityType.get(activityCondition.getType());
            if (type != null) {
                var expireDate = DateUtils.plusDays(now, 1);
                switch (type) {
                    case NEWS:
                        example.and().andGreaterThan("endTime", expireDate);
                        break;
                    case SOON_EXPIRE:
                        example.and().andLessThan("endTime", expireDate).andGreaterThan("endTime", now);
                        break;
                    case EXPIRE:
                        example.and().andLessThan("endTime", now);
                        break;
                    default:
                        break;
                }
            }
        }
        //未过期
        example.and().andGreaterThan("endTime", now);
        return selectByExample(example);
    }
}
