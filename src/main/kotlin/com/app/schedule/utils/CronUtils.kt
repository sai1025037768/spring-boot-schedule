package com.app.schedule.utils

import java.text.SimpleDateFormat
import java.util.*


object CronUtils{

    //"ss mm HH dd MM ? yyyy"
    private val sdf = SimpleDateFormat("? mm HH dd MM yyyy")

    fun formatDateByPattern(date: Date):String?{
        var formatTimeStr:String? = null
        if(Objects.nonNull(date)){
            formatTimeStr = sdf.format(date)
        }
        return formatTimeStr
    }

    fun getCron(date: Date):String?{
        return formatDateByPattern(date)
    }
}