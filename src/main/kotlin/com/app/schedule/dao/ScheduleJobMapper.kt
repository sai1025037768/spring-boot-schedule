package com.app.schedule.dao

import com.app.schedule.entity.ScheduleJobEntity
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:15:13
 */
@Mapper
interface ScheduleJobMapper : BaseMapper<ScheduleJobEntity?> {
    /**
     * 批量更新状态
     */
    fun updateBatch(map: Map<String?, Any?>?): Int
}