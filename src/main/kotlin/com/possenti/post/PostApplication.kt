package com.possenti.post

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.web.config.EnableSpringDataWebSupport

@EnableFeignClients
@SpringBootApplication
@EnableSpringDataWebSupport
class PostApplication

fun main(args: Array<String>) {
	runApplication<PostApplication>(*args)
}
