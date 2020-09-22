package com.app.schedule.utils

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/21
 * Time:9:45
 */
@Component
class SpringUtil : ApplicationContextAware {
    @Throws(BeansException::class)
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        if (Companion.applicationContext == null) {
            Companion.applicationContext = applicationContext
        }
        println("===============")
    }

    companion object {
        private var applicationContext: ApplicationContext? = null
        //获取applicationContext
        fun getApplicationContext(): ApplicationContext {
            return applicationContext!!
        }

        //通过name获取 Bean.
        fun getBean(name: String?): Any {
            return getApplicationContext().getBean(name!!)
        }

        //通过class获取Bean.
        fun <T> getBean(clazz: Class<T>): T {
            return getApplicationContext().getBean(clazz)
        }

        //通过name,以及Clazz返回指定的Bean
        fun <T> getBean(name: String?, clazz: Class<T>): T {
            return getApplicationContext().getBean(name!!, clazz)
        }
    }
}