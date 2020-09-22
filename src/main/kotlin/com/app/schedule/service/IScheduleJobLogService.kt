package com.app.schedule.service

import com.app.schedule.entity.ScheduleJobLogEntity
import com.app.schedule.utils.PageUtils
import com.baomidou.mybatisplus.extension.service.IService

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:15:02
 */
interface IScheduleJobLogService : IService<ScheduleJobLogEntity?> {
    fun queryPage(params: Map<String?, Any?>?): PageUtils<*>?
}