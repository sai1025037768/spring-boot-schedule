package com.app.schedule.entity

import java.io.Serializable
import java.util.*

/**
 * (TScheduleJob)实体类
 *
 * @author makejava
 * @since 2020-09-19 19:00:14
 */
class TScheduleJob : Serializable {
    var jobId: Int? = null
    var appName: String? = null
    var appIcon: String? = null
    var appPath: String? = null
    var scheduleTime: Date? = null
    var isScheduled: String? = null

    companion object {
        private const val serialVersionUID = -16831906080520165L
    }
}