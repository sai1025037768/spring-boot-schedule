package com.app.schedule.controller

import com.app.schedule.common.ResultMap
import com.app.schedule.entity.ScheduleJobEntity
import com.app.schedule.service.IScheduleJobService
import com.app.schedule.utils.ValidatorUtils.validateEntity
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:14:37
 */
@Api(value = "定时任务", tags = [" 定时任务"])
@RestController
@RequestMapping("/sys/schedule")
class QuartzApiController {
    @Autowired
    private val scheduleJobService: IScheduleJobService? = null

    /**
     * 定时任务列表
     */
    @ApiOperation(value = "定时任务列表", notes = "定时任务列表")
    @GetMapping("/list")
    fun list(@RequestParam params: Map<String?, Any?>?): ResultMap? {
        val page = scheduleJobService!!.queryPage(params)
        return ResultMap.Companion.ok().put("page", page)
    }

    /**
     * 定时任务信息
     */
    @ApiOperation(value = "定时任务信息", notes = "定时任务信息")
    @GetMapping("/info/{jobId}")
    fun info(@PathVariable("jobId") jobId: Long?): ResultMap? {
        val schedule = scheduleJobService!!.getById(jobId)
        return ResultMap.Companion.ok().put("schedule", schedule)
    }

    /**
     * 保存定时任务
     */
    @ApiOperation(value = "保存定时任务", notes = "保存定时任务")
    @PostMapping("/save")
    @Transactional
    fun save(@RequestBody scheduleJob: ScheduleJobEntity?): ResultMap {
        validateEntity(scheduleJob!!)
        scheduleJobService!!.insert(scheduleJob)
        return ResultMap.Companion.ok()
    }

    /**
     * 修改定时任务
     */
    @ApiOperation(value = "修改定时任务", notes = "修改定时任务")
    @PostMapping("/update")
    fun update(@RequestBody scheduleJob: ScheduleJobEntity?): ResultMap {
        validateEntity(scheduleJob!!)
        scheduleJobService!!.update(scheduleJob)
        return ResultMap.Companion.ok()
    }

    /**
     * 删除定时任务
     */
    @ApiOperation(value = "删除定时任务", notes = "删除定时任务")
    @PostMapping("/delete")
    fun delete(@RequestBody jobIds: Array<Long?>?): ResultMap {
        scheduleJobService!!.deleteBatch(jobIds)
        return ResultMap.Companion.ok()
    }

    /**
     * 立即执行任务
     */
    @ApiOperation(value = "立即执行任务", notes = "立即执行任务")
    @PostMapping("/run")
    fun run(@RequestBody jobIds: Array<Long?>?): ResultMap {
        scheduleJobService!!.run(jobIds)
        return ResultMap.Companion.ok()
    }

    /**
     * 暂停定时任务
     */
    @ApiOperation(value = "暂停定时任务", notes = "暂停定时任务")
    @PostMapping("/pause")
    fun pause(@RequestBody jobIds: Array<Long?>?): ResultMap {
        scheduleJobService!!.pause(jobIds)
        return ResultMap.Companion.ok()
    }

    /**
     * 恢复定时任务
     */
    @ApiOperation(value = "恢复定时任务", notes = "恢复定时任务")
    @PostMapping("/resume")
    fun resume(@RequestBody jobIds: Array<Long?>?): ResultMap {
        scheduleJobService!!.resume(jobIds)
        return ResultMap.Companion.ok()
    }
}