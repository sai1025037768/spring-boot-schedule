package com.app.schedule.dao

import com.app.schedule.entity.TAppFile
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * (TAppFile)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-19 18:59:55
 */
@Mapper
interface TAppFileDao: BaseMapper<TAppFile?> {
}