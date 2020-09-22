package com.app.schedule.utils

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

/**
 * Created with IDEA
 * @Description Spring Context 工具类
 * author:QinWei
 * Date:2019/5/20
 * Time:15:15
 */
@Component
class SpringContextUtils : ApplicationContextAware {
    @Throws(BeansException::class)
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        Companion.applicationContext = applicationContext
    }

    companion object {
        var applicationContext: ApplicationContext? = null
        fun getBean(name: String?): Any {
            return applicationContext!!.getBean(name!!)
        }

        fun <T> getBean(name: String?, requiredType: Class<T>): T {
            return applicationContext!!.getBean(name!!, requiredType)
        }

        fun containsBean(name: String?): Boolean {
            return applicationContext!!.containsBean(name!!)
        }

        fun isSingleton(name: String?): Boolean {
            return applicationContext!!.isSingleton(name!!)
        }

        fun getType(name: String?): Class<out Any> {
            return applicationContext!!.getType(name!!)!!
        }
    }
}