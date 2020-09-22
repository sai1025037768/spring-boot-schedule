package com.app.schedule.service.impl

import com.app.schedule.dao.TScheduleJobDao
import com.app.schedule.entity.TScheduleJob
import com.app.schedule.service.TScheduleJobService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * (TScheduleJob)表服务实现类
 *
 * @author makejava
 * @since 2020-09-19 19:00:14
 */
@Service("tScheduleJobService")
class TScheduleJobServiceImpl : TScheduleJobService {
    @Resource
    private val tScheduleJobDao: TScheduleJobDao? = null

    /**
     * 通过ID查询单条数据
     *
     * @param jobId 主键
     * @return 实例对象
     */
    override fun queryById(jobId: Int?): TScheduleJob? {
        return tScheduleJobDao!!.queryById(jobId)
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    override fun queryAllByLimit(offset: Int, limit: Int): List<TScheduleJob?>? {
        return tScheduleJobDao!!.queryAllByLimit(offset, limit)
    }

    /**
     * 新增数据
     *
     * @param tScheduleJob 实例对象
     * @return 实例对象
     */
    override fun insert(tScheduleJob: TScheduleJob?): TScheduleJob? {
        tScheduleJobDao!!.insert(tScheduleJob)
        return tScheduleJob
    }

    /**
     * 修改数据
     *
     * @param tScheduleJob 实例对象
     * @return 实例对象
     */
    override fun update(tScheduleJob: TScheduleJob): TScheduleJob? {
        tScheduleJobDao!!.update(tScheduleJob)
        return queryById(tScheduleJob.jobId)
    }

    /**
     * 通过主键删除数据
     *
     * @param jobId 主键
     * @return 是否成功
     */
    override fun deleteById(jobId: Int?): Boolean {
        return tScheduleJobDao!!.deleteById(jobId) > 0
    }
}