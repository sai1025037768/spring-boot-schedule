package com.app.schedule.common

import com.app.schedule.common.exception.GlobalException
import com.app.schedule.utils.SpringContextUtils
import org.apache.commons.lang3.StringUtils
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Method

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:15:17
 */
class ScheduleRunnable(beanName: String?, methodName: String?, params: String?) : Runnable {
    private val target: Any
    private var method: Method? = null
    private val params: String?
    override fun run() {
        try {
            ReflectionUtils.makeAccessible(method!!)
            if (StringUtils.isNotBlank(params)) {
                method?.invoke(target, params)
            } else {
                method?.invoke(target)
            }
        } catch (e: Exception) {
            throw GlobalException("执行定时任务失败", e)
        }
    }

    init {
        target = SpringContextUtils.getBean(beanName)
        this.params = params
        if (StringUtils.isNotBlank(params)) {
            method = target.javaClass.getDeclaredMethod(methodName, String::class.java)
        } else {
            method = target.javaClass.getDeclaredMethod(methodName)
        }
    }
}