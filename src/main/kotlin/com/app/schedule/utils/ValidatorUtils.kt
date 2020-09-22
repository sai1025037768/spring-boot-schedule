package com.app.schedule.utils

import com.app.schedule.common.exception.GlobalException
import javax.validation.Validation
import javax.validation.Validator

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:15:50
 */
object ValidatorUtils {
    private var validator: Validator? = null
    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws GlobalException  校验不通过，则报RRException异常
     */
    @JvmStatic
    @Throws(GlobalException::class)
    fun validateEntity(`object`: Any, vararg groups: Class<*>?) {
        val constraintViolations = validator!!.validate(`object`, *groups)
        if (!constraintViolations.isEmpty()) {
            val msg = StringBuilder()
            for (constraint in constraintViolations) {
                msg.append(constraint.message).append("<br>")
            }
            throw GlobalException(msg.toString())
        }
    }

    init {
        validator = Validation.buildDefaultValidatorFactory().validator
    }
}