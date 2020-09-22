package com.app.schedule.entity

import com.baomidou.mybatisplus.annotation.TableId
import java.io.Serializable
import java.util.*

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:15:03
 */
class ScheduleJobLogEntity : Serializable {
    /**
     * 日志id
     */
    @TableId
    var logId: Long? = null
    /**
     * 任务id
     */
    var jobId: Long? = null
    /**
     * spring bean名称
     */
    var beanName: String? = null
    /**
     * 方法名
     */
    var methodName: String? = null
    /**
     * 参数
     */
    var params: String? = null
    /**
     * 任务状态    0：成功    1：失败
     */
    var status: Int? = null
    /**
     * 失败信息
     */
    var error: String? = null
    /**
     * 耗时(单位：毫秒)
     */
    var times: Int? = null
    /**
     * 创建时间
     */
    var createTime: Date? = null

    companion object {
        private const val serialVersionUID = -1952642364611337791L
    }
}