package com.app.schedule.common

import org.apache.http.HttpStatus
import java.util.*

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:15:44
 */
class ResultMap : HashMap<String?, Any?>() {
    override fun put(key: String?, value: Any?): ResultMap? {
        super.put(key, value)
        return this
    }

    companion object {
        @JvmOverloads
        fun error(code: Int = HttpStatus.SC_INTERNAL_SERVER_ERROR, msg: String? = "未知异常，请联系管理员"): ResultMap {
            val r = ResultMap()
            r["code"] = code
            r["msg"] = msg
            return r
        }

        fun error(msg: String?): ResultMap {
            return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg)
        }

        fun ok(msg: String?): ResultMap {
            val r = ResultMap()
            r["msg"] = msg
            return r
        }

        fun ok(par: Map<String?, Any?>): ResultMap {
            val r = ResultMap()
            r.putAll(par)
            return r
        }

        fun ok(): ResultMap {
            return ResultMap()
        }
    }

    init {
        put("code", 0)
        put("msg", "success")
    }
}