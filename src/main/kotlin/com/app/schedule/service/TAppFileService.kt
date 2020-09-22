package com.app.schedule.service

import com.app.schedule.entity.TAppFile
import com.baomidou.mybatisplus.extension.service.IService

/**
 * (TAppFile)表服务接口
 *
 * @author makejava
 * @since 2020-09-19 18:59:56
 */
interface TAppFileService : IService<TAppFile?> {

    fun queryById(id: Int): TAppFile?
}