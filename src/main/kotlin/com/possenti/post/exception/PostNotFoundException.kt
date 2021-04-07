package com.possenti.post.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
class PostNotFoundException(override val message: String? = "Post not found.", e: Throwable? = null) : RuntimeException(message, e)