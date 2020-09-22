package com.app.schedule.dao

import com.app.schedule.entity.TScheduleJob
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * (TScheduleJob)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-19 19:00:14
 */
@Mapper
interface TScheduleJobDao: BaseMapper<TScheduleJob?> {

}