package com.app.schedule.config

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:15:14
 */
object Constant {
    /** 超级管理员ID  */
    const val SUPER_ADMIN = 1

    /**
     * 菜单类型
     *
     */
    enum class MenuType(val value: Int) {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

    }

    /**
     * 定时任务状态
     */
    enum class ScheduleStatus(val value: Int) {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

    }

    /**
     * 文件存储
     */
    enum class CloudService(val value: Int) {
        /**
         * 本地
         */
        LOCAL(0),
        /**
         * fastdfs
         */
        FASTDFS(1),
        /**
         * 七牛云
         */
        QINIU(2),
        /**
         * 阿里云
         */
        ALIYUN(3),
        /**
         * 腾讯云
         */
        QCLOUD(4);

    }
}