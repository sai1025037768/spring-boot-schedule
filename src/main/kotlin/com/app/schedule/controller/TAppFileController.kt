package com.app.schedule.controller

import com.app.schedule.entity.TAppFile
import com.app.schedule.service.TAppFileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

/**
 * (TAppFile)表控制层
 *
 * @author makejava
 * @since 2020-09-19 18:59:57
 */
@RestController
@RequestMapping("tAppFile")
class TAppFileController {
    /**
     * 服务对象
     */
    @Autowired
    private val tAppFileService: TAppFileService? = null

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    fun selectOne(id: Int): TAppFile? {
        return tAppFileService!!.queryById(id)
    }
}