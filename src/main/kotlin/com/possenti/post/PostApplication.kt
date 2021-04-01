package com.possenti.post

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class PostApplication

fun main(args: Array<String>) {
	runApplication<PostApplication>(*args)
}
