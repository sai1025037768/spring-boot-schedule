package com.app.schedule.utils

import com.app.schedule.common.exception.GlobalException
import com.app.schedule.config.Constant
import com.app.schedule.config.ScheduleJob
import com.app.schedule.entity.ScheduleJobEntity
import org.quartz.*

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:14:57
 */
object ScheduleUtils {
    private const val JOB_NAME = "TASK_"
    /**
     * 获取触发器key
     */
    fun getTriggerKey(jobId: Long): TriggerKey {
        return TriggerKey.triggerKey(JOB_NAME + jobId)
    }

    /**
     * 获取jobKey
     */
    fun getJobKey(jobId: Long): JobKey {
        return JobKey.jobKey(JOB_NAME + jobId)
    }

    /**
     * 获取表达式触发器
     */
    @JvmStatic
    fun getCronTrigger(scheduler: Scheduler, jobId: Long?): CronTrigger {
        return try {
            (scheduler.getTrigger(getTriggerKey(jobId!!)) as CronTrigger)
        } catch (e: SchedulerException) {
            throw GlobalException("获取定时任务CronTrigger出现异常", e)
        }
    }

    /**
     * 创建定时任务
     */
    @JvmStatic
    fun createScheduleJob(scheduler: Scheduler, scheduleJob: ScheduleJobEntity) {
        try { //构建job信息
            val jobDetail = JobBuilder.newJob(ScheduleJob::class.java).withIdentity(getJobKey(scheduleJob.jobId!!))
                    .build()
            //表达式调度构建器
            val scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.cronExpression)
                    .withMisfireHandlingInstructionDoNothing()
            //按新的cronExpression表达式构建一个新的trigger
            val trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(scheduleJob.jobId!!)).withSchedule(scheduleBuilder).build()
            //放入参数，运行时的方法可以获取
            jobDetail.jobDataMap[ScheduleJobEntity.JOB_PARAM_KEY] = scheduleJob
            scheduler.scheduleJob(jobDetail, trigger)
            //暂停任务
            if (scheduleJob.status == Constant.ScheduleStatus.PAUSE.value) {
                pauseJob(scheduler, scheduleJob.jobId)
            }
        } catch (e: SchedulerException) {
            throw GlobalException("创建定时任务失败", e)
        }
    }

    /**
     * 更新定时任务
     */
    @JvmStatic
    fun updateScheduleJob(scheduler: Scheduler, scheduleJob: ScheduleJobEntity) {
        try {
            val triggerKey = getTriggerKey(scheduleJob.jobId!!)
            //表达式调度构建器
            val scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.cronExpression)
                    .withMisfireHandlingInstructionDoNothing()
            var trigger = getCronTrigger(scheduler, scheduleJob.jobId)
            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.triggerBuilder.withIdentity(triggerKey).withSchedule(scheduleBuilder).build()
            //参数
            trigger.jobDataMap[ScheduleJobEntity.JOB_PARAM_KEY] = scheduleJob
            scheduler.rescheduleJob(triggerKey, trigger)
            //暂停任务
            if (scheduleJob.status == Constant.ScheduleStatus.PAUSE.value) {
                pauseJob(scheduler, scheduleJob.jobId)
            }
        } catch (e: SchedulerException) {
            throw GlobalException("更新定时任务失败", e)
        }
    }

    /**
     * 立即执行任务
     */
    @JvmStatic
    fun run(scheduler: Scheduler, scheduleJob: ScheduleJobEntity) {
        try { //参数
            val dataMap = JobDataMap()
            dataMap[ScheduleJobEntity.JOB_PARAM_KEY] = scheduleJob
            scheduler.triggerJob(getJobKey(scheduleJob.jobId!!), dataMap)
        } catch (e: SchedulerException) {
            throw GlobalException("立即执行定时任务失败", e)
        }
    }

    /**
     * 暂停任务
     */
    @JvmStatic
    fun pauseJob(scheduler: Scheduler, jobId: Long?) {
        try {
            scheduler.pauseJob(getJobKey(jobId!!))
        } catch (e: SchedulerException) {
            throw GlobalException("暂停定时任务失败", e)
        }
    }

    /**
     * 恢复任务
     */
    @JvmStatic
    fun resumeJob(scheduler: Scheduler, jobId: Long?) {
        try {
            scheduler.resumeJob(getJobKey(jobId!!))
        } catch (e: SchedulerException) {
            throw GlobalException("暂停定时任务失败", e)
        }
    }

    /**
     * 删除定时任务
     */
    @JvmStatic
    fun deleteScheduleJob(scheduler: Scheduler, jobId: Long?) {
        try {
            scheduler.deleteJob(getJobKey(jobId!!))
        } catch (e: SchedulerException) {
            throw GlobalException("删除定时任务失败", e)
        }
    }
}