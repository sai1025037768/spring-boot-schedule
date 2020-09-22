package com.app.schedule.controller

import com.app.schedule.entity.TScheduleJob
import com.app.schedule.service.TScheduleJobService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

/**
 * (TScheduleJob)表控制层
 *
 * @author makejava
 * @since 2020-09-19 19:00:14
 */
@RestController
@RequestMapping("tScheduleJob")
class TScheduleJobController {
    /**
     * 服务对象
     */
    @Resource
    private val tScheduleJobService: TScheduleJobService? = null

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    fun selectOne(id: Int?): TScheduleJob? {
        return tScheduleJobService!!.queryById(id)
    }
}