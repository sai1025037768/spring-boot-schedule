package com.app.schedule.service.impl

import com.app.schedule.config.Constant
import com.app.schedule.dao.ScheduleJobMapper
import com.app.schedule.entity.ScheduleJobEntity
import com.app.schedule.entity.ScheduleJobLogEntity
import com.app.schedule.service.IScheduleJobService
import com.app.schedule.utils.PageUtils
import com.app.schedule.utils.ScheduleUtils.createScheduleJob
import com.app.schedule.utils.ScheduleUtils.deleteScheduleJob
import com.app.schedule.utils.ScheduleUtils.getCronTrigger
import com.app.schedule.utils.ScheduleUtils.pauseJob
import com.app.schedule.utils.ScheduleUtils.resumeJob
import com.app.schedule.utils.ScheduleUtils.run
import com.app.schedule.utils.ScheduleUtils.updateScheduleJob
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.apache.commons.lang3.StringUtils
import org.quartz.Scheduler
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.annotation.PostConstruct
import javax.annotation.Resource

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:14:50
 */
@Service("scheduleJobService")
class IScheduleJobServiceImpl : ServiceImpl<ScheduleJobMapper?, ScheduleJobEntity?>(), IScheduleJobService {
    @Resource
    private val scheduler: Scheduler? = null

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    fun init() {
        val scheduleJobList = this.list()
        for (scheduleJob in scheduleJobList) {
            val cronTrigger = getCronTrigger(scheduler!!, scheduleJob!!.jobId)
            //如果不存在，则创建
            if (cronTrigger == null) {
                createScheduleJob(scheduler, scheduleJob)
            } else {
                updateScheduleJob(scheduler, scheduleJob)
            }
        }
    }

    override fun queryPage(params: Map<String?, Any?>?): PageUtils<*> {
        val beanName = params!!["beanName"] as String?
        val page: Page<ScheduleJobEntity> = PageUtils<ScheduleJobEntity>(params).getPage()
        val queryWrapper = QueryWrapper<ScheduleJobEntity>().like(StringUtils.isNotBlank(beanName), "job_id", beanName).orderByDesc("job_id")
        val list = this.page(page, queryWrapper).getRecords()
        page.setRecords(list)
        return PageUtils<ScheduleJobLogEntity>(page)
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun insert(scheduleJob: ScheduleJobEntity?) {
        scheduleJob!!.createTime = Date()
        scheduleJob!!.status = Constant.ScheduleStatus.NORMAL.value
        save(scheduleJob)
        createScheduleJob(scheduler!!, scheduleJob)
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun update(scheduleJob: ScheduleJobEntity?) {
        updateScheduleJob(scheduler!!, scheduleJob!!)
        updateById(scheduleJob)
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun deleteBatch(jobIds: Array<Long?>?) {
        for (jobId in jobIds!!) {
            deleteScheduleJob(scheduler!!, jobId)
        }
        //删除数据
        removeByIds(Arrays.asList(*jobIds))
    }

    override fun updateBatch(jobIds: Array<Long?>?, status: Int): Int {
        val map = HashMap<String?, Any?>()
        map["list"] = jobIds
        map["status"] = status
        return baseMapper!!.updateBatch(map)
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun run(jobIds: Array<Long?>?) {
        for (jobId in jobIds!!) {
            run(scheduler!!, getById(jobId)!!)
        }
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun pause(jobIds: Array<Long?>?) {
        for (jobId in jobIds!!) {
            pauseJob(scheduler!!, jobId)
        }
        updateBatch(jobIds, Constant.ScheduleStatus.PAUSE.value)
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun resume(jobIds: Array<Long?>?) {
        for (jobId in jobIds!!) {
            resumeJob(scheduler!!, jobId)
        }
        updateBatch(jobIds, Constant.ScheduleStatus.NORMAL.value)
    }
}