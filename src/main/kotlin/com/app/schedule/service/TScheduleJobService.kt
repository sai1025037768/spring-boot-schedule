package com.app.schedule.service

import com.app.schedule.entity.TScheduleJob

/**
 * (TScheduleJob)表服务接口
 *
 * @author makejava
 * @since 2020-09-19 19:00:14
 */
interface TScheduleJobService {
    /**
     * 通过ID查询单条数据
     *
     * @param jobId 主键
     * @return 实例对象
     */
    fun queryById(jobId: Int?): TScheduleJob?

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    fun queryAllByLimit(offset: Int, limit: Int): List<TScheduleJob?>?

    /**
     * 新增数据
     *
     * @param tScheduleJob 实例对象
     * @return 实例对象
     */
    fun insert(tScheduleJob: TScheduleJob?): TScheduleJob?

    /**
     * 修改数据
     *
     * @param tScheduleJob 实例对象
     * @return 实例对象
     */
    fun update(tScheduleJob: TScheduleJob): TScheduleJob?

    /**
     * 通过主键删除数据
     *
     * @param jobId 主键
     * @return 是否成功
     */
    fun deleteById(jobId: Int?): Boolean
}