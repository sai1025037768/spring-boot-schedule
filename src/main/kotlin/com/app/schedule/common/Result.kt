package com.app.schedule.common

import java.util.*

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/21
 * Time:9:05
 */
class Result : HashMap<String?, Any?>() {
    override fun put(key: String?, value: Any?): Result {
        super.put(key, value)
        return this
    }

    companion object {
        private const val serialVersionUID = 8692945724510161753L
        fun error(msg: String): Result {
            return error(500, msg)
        }

        @JvmOverloads
        fun error(code: Int = 500, msg: String = "未知异常，请联系管理员"): Result {
            val r = Result()
            r["code"] = code
            r["msg"] = msg
            return r
        }

        fun error(msg: Any): Result {
            val r = Result()
            r["msg"] = msg
            return r
        }

        fun ok(msg: Any): Result {
            val r = Result()
            r["msg"] = msg
            return r
        }

        fun ok(map: Map<String?, Any?>): Result {
            val r = Result()
            r.putAll(map)
            return r
        }

        fun ok(): Result {
            return Result()
        }
    }

    init {
        put("code", 0)
    }
}