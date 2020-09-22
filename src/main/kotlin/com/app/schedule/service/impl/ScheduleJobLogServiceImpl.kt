package com.app.schedule.service.impl

import com.app.schedule.dao.ScheduleJobLogMapper
import com.app.schedule.entity.ScheduleJobLogEntity
import com.app.schedule.service.IScheduleJobLogService
import com.app.schedule.utils.PageUtils
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Service

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:15:02
 */
@Service("scheduleJobLogService")
class ScheduleJobLogServiceImpl : ServiceImpl<ScheduleJobLogMapper?, ScheduleJobLogEntity?>(), IScheduleJobLogService {
    override fun queryPage(params: Map<String?, Any?>?): PageUtils<*> {
        val jobId = params!!["jobId"] as String?
        val page: Page<ScheduleJobLogEntity> = PageUtils<ScheduleJobLogEntity>(params).getPage()
        val queryWrapper = QueryWrapper<ScheduleJobLogEntity>().like(StringUtils.isNotBlank(jobId), "job_id", jobId).orderByDesc("log_id")
        val list = this.page(page, queryWrapper).getRecords()
        page.setRecords(list)
        return PageUtils<ScheduleJobLogEntity>(page)
    }
}