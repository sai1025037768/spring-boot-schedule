package com.app.schedule.service.impl

import com.app.schedule.dao.TAppFileDao
import com.app.schedule.entity.TAppFile
import com.app.schedule.service.TAppFileService
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * (TAppFile)表服务实现类
 *
 * @author makejava
 * @since 2020-09-19 18:59:57
 */
@Service("tAppFileService")
class TAppFileServiceImpl  : ServiceImpl<TAppFileDao?, TAppFile?>(), TAppFileService {

    override fun queryById(id: Int): TAppFile? {
        return baseMapper!!.selectById(id)
    }


}