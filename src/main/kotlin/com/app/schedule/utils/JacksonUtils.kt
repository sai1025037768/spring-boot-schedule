package com.app.schedule.utils

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:15:10
 */
object JacksonUtils {
    private var objectMapper: ObjectMapper? = null
    /**
     * 将对象序列化json字符串
     * @param t 对象可以是 String Map、List
     * @return json字符串
     */
    fun <T> toJson(t: T): String {
        return try {
            objectMapper!!.writeValueAsString(t)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }
    }

    /**
     * 字符串转Java对象
     * JsonNode可适用本方法
     * @param json
     * @param clazz
     * @return t
     */
    fun <T> toObject(json: String?, clazz: Class<T>?): T {
        return try {
            objectMapper!!.readValue(json, clazz)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    /**
     * json序列化
     * @param json
     * @param typeReference
     * @return
     */
    fun <T> toCollection(json: String?, typeReference: TypeReference<T>?): T {
        return try {
            objectMapper!!.readValue(json, typeReference)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    /**
     * 获取jsonNode对象
     * @param json
     * @return
     */
    fun getJsonNode(json: String?): JsonNode? {
        try {
            return objectMapper!!.readValue(json, JsonNode::class.java)
        } catch (e: JsonParseException) {
            e.printStackTrace()
        } catch (e: JsonMappingException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 将obj对象转换成 class类型的对象
     * @param obj
     * @param clazz
     * @return
     */
    fun <T> parseObject(obj: Any?, clazz: Class<T>?): T {
        return JSON.parseObject(JSON.toJSONString(obj), clazz)
    }

    /**
     * 将String类型转换为JSONObject对象
     * @param text
     * @return
     */
    fun getJSONObject(text: String?): JSONObject {
        return JSON.parseObject(text)
    }

    init {
        objectMapper = ObjectMapper().apply {
            //去掉默认的时间戳格式
            configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)

            //设置为中国上海时区
            setTimeZone(TimeZone.getTimeZone("GMT+8"))
                    //空值不序列化
                   setSerializationInclusion(JsonInclude.Include.NON_NULL)
            //反序列化时，属性不存在的兼容处理
           getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            //序列化时，日期统一格式
            setDateFormat(SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
            configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            //单引号处理
            configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
        }


    }
}