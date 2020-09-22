package com.app.schedule.common

import com.app.schedule.common.exception.GlobalException
import org.apache.commons.lang3.StringUtils

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:15:09
 */
object SQLFilter {
    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    fun sqlInject(str: String): String? {
        var str = str
        if (StringUtils.isBlank(str)) {
            return null
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "")
        str = StringUtils.replace(str, "\"", "")
        str = StringUtils.replace(str, ";", "")
        str = StringUtils.replace(str, "\\", "")
        //转换成小写
        str = str.toLowerCase()
        //非法字符
        val keywords = arrayOf("master", "truncate", "save", "select", "delete", "update", "declare", "alert", "drop")
        //判断是否包含非法字符
        for (keyword in keywords) {
            if (str.indexOf(keyword) != -1) {
                throw GlobalException("包含非法字符")
            }
        }
        return str
    }
}