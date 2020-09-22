package com.app.schedule.entity

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.util.*
import javax.validation.constraints.NotBlank

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:14:55
 */
@TableName("t_schedule_job")
class ScheduleJobEntity : Serializable {
    /**
     * 获取：任务id
     *
     * @return Long
     */
    /**
     * 设置：任务id
     *
     * @param jobId 任务id
     */
    /**
     * 任务id
     */
    @TableId
    var jobId: Long? = null
    /**
     * spring bean名称
     */
    var beanName: @NotBlank(message = "bean名称不能为空") String? = null
    /**
     * 方法名
     */
    var methodName: @NotBlank(message = "方法名称不能为空") String? = null
    /**
     * 参数
     */
    var params: String? = null
    /**
     * 获取：cron表达式
     *
     * @return String
     */
    /**
     * 设置：cron表达式
     *
     * @param cronExpression cron表达式
     */
    /**
     * cron表达式
     */
    var cronExpression: @NotBlank(message = "cron表达式不能为空") String? = null
    /**
     * 获取：任务状态
     *
     * @return String
     */
    /**
     * 设置：任务状态
     *
     * @param status 任务状态
     */
    /**
     * 任务状态
     */
    var status: Int? = null
    /**
     * 备注
     */
    var remark: String? = null
    /**
     * 获取：创建时间
     *
     * @return Date
     */
    /**
     * 设置：创建时间
     *
     * @param createTime 创建时间
     */
    /**
     * 创建时间
     */
    @get:JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    var createTime: Date? = null

    companion object {
        private const val serialVersionUID = 8014569802705713112L
        /**
         * 任务调度参数key
         */
        const val JOB_PARAM_KEY = "JOB_PARAM_KEY"
    }
}