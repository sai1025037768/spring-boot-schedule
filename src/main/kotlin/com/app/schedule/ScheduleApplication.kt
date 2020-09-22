package com.app.schedule

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@MapperScan(basePackages = arrayOf("com.app.schedule.dao"))
class ScheduleApplication

fun main(args: Array<String>) {
    runApplication<ScheduleApplication>(*args)
}
