package com.app.schedule.config

import com.google.common.collect.Sets
import io.swagger.annotations.ApiOperation
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * Created with IDEA
 * author:QinWei
 * Date:2019/5/20
 * Time:16:14
 */
@Configuration
@EnableSwagger2
class Swagger2 {
    @Bean
    fun createRestApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json"))
                .consumes(Sets.newHashSet("application/json"))
                .protocols(Sets.newHashSet("http", "https"))
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .useDefaultResponseMessages(false).select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation::class.java))
                .paths(PathSelectors.any()).build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder() // 文档标题
                .title("系统API服务") // 文档描述
                .description("系统API接口文档简要描述")
                .version("v1")
                .license("MIT 协议")
                .licenseUrl("http://www.opensource.org/licenses/MIT")
                .contact(Contact("TuMinglong", "https://github.com/tumao2/hdw-dubbo", "tuminglong@126.com")).build()
    }
}