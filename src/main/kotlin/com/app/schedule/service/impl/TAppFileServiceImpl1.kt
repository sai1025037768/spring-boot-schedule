package com.app.schedule.service.impl

import com.app.schedule.dao.TAppFileDao
import com.app.schedule.entity.TAppFile
import com.app.schedule.service.TAppFileService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * (TAppFile)表服务实现类
 *
 * @author makejava
 * @since 2020-09-19 18:59:57
 */
@Service("tAppFileService")
class TAppFileServiceImpl : TAppFileService {
    @Resource
    private val tAppFileDao: TAppFileDao? = null

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    override fun queryById(id: Int?): TAppFile? {
        return tAppFileDao!!.selectById(id)
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
//    override fun queryAllByLimit(offset: Int, limit: Int): List<TAppFile?>? {
//        return tAppFileDao!!.sel(offset, limit)
//    }

    /**
     * 新增数据
     *
     * @param tAppFile 实例对象
     * @return 实例对象
     */
    override fun insert(tAppFile: TAppFile?): TAppFile? {
        tAppFileDao!!.insert(tAppFile)
        return tAppFile
    }

    /**
     * 修改数据
     *
     * @param tAppFile 实例对象
     * @return 实例对象
     */
//    override fun update(tAppFile: TAppFile): TAppFile? {
//        tAppFileDao!!.update(tAppFile)
//        return queryById(tAppFile.id)
//    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    override fun deleteById(id: Int?): Boolean {
        return tAppFileDao!!.deleteById(id) > 0
    }
}