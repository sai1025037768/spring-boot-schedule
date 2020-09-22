package com.app.schedule.common.exception

/**
 * Created with IDEA
 * @Description 自定义异常
 * author:QinWei
 * Date:2019/5/20
 * Time:14:58
 */
class GlobalException : RuntimeException {
    var msg: String
    var code = 500

    constructor(msg: String) : super(msg) {
        this.msg = msg
    }

    constructor(msg: String, e: Throwable?) : super(msg, e) {
        this.msg = msg
    }

    constructor(msg: String, code: Int) : super(msg) {
        this.msg = msg
        this.code = code
    }

    constructor(msg: String, code: Int, e: Throwable?) : super(msg, e) {
        this.msg = msg
        this.code = code
    }

}