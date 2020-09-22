package com.app.schedule.service

import com.app.schedule.entity.ScheduleJobEntity
import com.app.schedule.utils.PageUtils
import com.baomidou.mybatisplus.extension.service.IService

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:14:49
 */
interface IScheduleJobService : IService<ScheduleJobEntity?> {
    fun queryPage(params: Map<String?, Any?>?): PageUtils<*>?
    /**
     * 保存定时任务
     */
    fun insert(scheduleJob: ScheduleJobEntity?)

    /**
     * 更新定时任务
     */
    fun update(scheduleJob: ScheduleJobEntity?)

    /**
     * 批量删除定时任务
     */
    fun deleteBatch(jobIds: Array<Long?>?)

    /**
     * 批量更新定时任务状态
     */
    fun updateBatch(jobIds: Array<Long?>?, status: Int): Int

    /**
     * 立即执行
     */
    fun run(jobIds: Array<Long?>?)

    /**
     * 暂停运行
     */
    fun pause(jobIds: Array<Long?>?)

    /**
     * 恢复运行
     */
    fun resume(jobIds: Array<Long?>?)
}