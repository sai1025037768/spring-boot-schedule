package com.app.schedule.service

import com.app.schedule.entity.TAppFile

/**
 * (TAppFile)表服务接口
 *
 * @author makejava
 * @since 2020-09-19 18:59:56
 */
interface TAppFileService {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    fun queryById(id: Int?): TAppFile?

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    fun queryAllByLimit(offset: Int, limit: Int): List<TAppFile?>?

    /**
     * 新增数据
     *
     * @param tAppFile 实例对象
     * @return 实例对象
     */
    fun insert(tAppFile: TAppFile?): TAppFile?

    /**
     * 修改数据
     *
     * @param tAppFile 实例对象
     * @return 实例对象
     */
    fun update(tAppFile: TAppFile): TAppFile?

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    fun deleteById(id: Int?): Boolean
}