package com.app.schedule.config

import com.app.schedule.common.ScheduleRunnable
import com.app.schedule.entity.ScheduleJobLogEntity
import com.app.schedule.service.IScheduleJobLogService
import com.app.schedule.service.IScheduleJobService
import com.app.schedule.utils.SpringContextUtils
import org.apache.commons.lang3.StringUtils
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.slf4j.LoggerFactory
import org.springframework.scheduling.quartz.QuartzJobBean
import java.util.*
import java.util.concurrent.Executors

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:15:00
 */
class ScheduleJob : QuartzJobBean() {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val service = Executors.newSingleThreadExecutor()
    @Throws(JobExecutionException::class)
    override fun executeInternal(context: JobExecutionContext) {
        val jobKey = context.jobDetail.key
        val scheduleJobId = jobKey.name.substring(jobKey.name.indexOf("_") + 1)
        //获取spring bean
        val scheduleJobService = SpringContextUtils.getBean("scheduleJobService") as IScheduleJobService
        val scheduleJob = scheduleJobService.getById(java.lang.Long.valueOf(scheduleJobId))
        //获取spring bean
        val scheduleJobLogService = SpringContextUtils.getBean("scheduleJobLogService") as IScheduleJobLogService
        //数据库保存执行记录
        val log = ScheduleJobLogEntity()
        log.jobId = scheduleJob!!.jobId
        log.beanName = scheduleJob!!.beanName
        log.methodName = scheduleJob!!.methodName
        log.params = scheduleJob!!.params
        log.createTime = Date()
        //任务开始时间
        val startTime = System.currentTimeMillis()
        try { //执行任务
            logger.info("任务准备执行，任务ID：" + scheduleJob.jobId)
            val task = ScheduleRunnable(scheduleJob.beanName,
                    scheduleJob.methodName, scheduleJob.params)
            val future = service.submit(task)
            future.get()
            //任务执行总时长
            val times = System.currentTimeMillis() - startTime
            log.times = times.toInt()
            //任务状态    0：成功    1：失败
            log.status = 0
            logger.info("任务执行完毕，任务ID：" + scheduleJob.jobId + "  总共耗时：" + times + "毫秒")
        } catch (e: Exception) {
            logger.error("任务执行失败，任务ID：" + scheduleJob.jobId, e)
            //任务执行总时长
            val times = System.currentTimeMillis() - startTime
            log.times = times.toInt()
            //任务状态    0：成功    1：失败
            log.status = 1
            log.error = StringUtils.substring(e.toString(), 0, 2000)
        } finally {
            scheduleJobLogService.save(log)
        }
    }
}