package com.app.schedule.utils

import com.app.schedule.common.SQLFilter
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.fasterxml.jackson.annotation.JsonIgnore
import org.apache.commons.lang3.StringUtils
import java.io.Serializable

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:14:51
 */
class PageUtils<T> : Serializable {
    //总记录数
    var totalCount: Long = 0
        private set
    //每页记录数
    var pageSize: Long = 0
        private set
    //总页数
    var totalPage: Long = 0
        private set
    //当前页数
    var currPage: Long = 0
        private set
    //列表数据
    private var list: List<*>? = null
    /**
     * mybatis-plus分页参数
     */
    @JsonIgnore
    private var page: Page<T>? = null

    constructor() : super() {}
    /**
     * 分页
     */
    constructor(page: IPage<*>) {
        list = page.records
        totalCount = page.total
        pageSize = page.size
        currPage = page.current
        totalPage = page.pages
    }

    constructor(params: Map<String?, Any?>) { //当前页码
        var page: Long? = 1L
        //当前行数
        var limit: Long? = 10L
        //分页参数
        if (params["page"] != null) {
            page = java.lang.Long.valueOf(params["page"].toString())
        }
        if (params["limit"] != null) {
            limit = java.lang.Long.valueOf(params["limit"].toString())
        }
        //防止SQL注入（因为asc、order是通过拼接SQL实现排序的，会有SQL注入风险）
        val asc = SQLFilter.sqlInject(params["asc"] as String)
        val desc = SQLFilter.sqlInject(params["desc"] as String)
        //mybatis-plus分页
        this.page = Page(page!!, limit!!)
        //排序
        if (StringUtils.isNotBlank(asc) && StringUtils.isNotBlank(desc)) {
            if (asc!!.indexOf(",") > -1) {
                val ascs = asc.split(",").toTypedArray()
                this.page?.setAsc(*ascs)
            } else {
                this.page?.setAsc(*arrayOf(asc))
            }
            if (asc.indexOf(",") > -1) {
                val descs = desc?.split(",")!!.toTypedArray()
                this.page?.setDesc(*descs)
            } else {
                this.page?.setDesc(*arrayOf(desc))
            }
        }
    }

    fun getPage(): Page<T> {
        return page!!
    }

    fun setTotalCount(totalCount: Int) {
        this.totalCount = totalCount.toLong()
    }

    fun setPageSize(pageSize: Int) {
        this.pageSize = pageSize.toLong()
    }

    fun setTotalPage(totalPage: Int) {
        this.totalPage = totalPage.toLong()
    }

    fun setCurrPage(currPage: Int) {
        this.currPage = currPage.toLong()
    }

    fun getList(): List<*> {
        return list!!
    }

    fun setList(list: List<*>?) {
        this.list = list
    }

    override fun toString(): String {
        return JacksonUtils.toJson(this)
    }

    companion object {
        private const val serialVersionUID = -4533666692589349967L
    }
}