package com.app.schedule.entity

import com.baomidou.mybatisplus.annotation.TableId
import java.io.Serializable

/**
 * (TAppFile)实体类
 *
 * @author makejava
 * @since 2020-09-19 18:59:53
 */
class TAppFile : Serializable {

    @TableId
    var id: Int? = null
    var jobId: String? = null
    var appName: String? = null
    var appIcon: String? = null
    var appPath: String? = null
    var appVersion: Int? = null
    var appVersionCode: String? = null

    companion object {
        private const val serialVersionUID = 755109636294200713L
    }
}