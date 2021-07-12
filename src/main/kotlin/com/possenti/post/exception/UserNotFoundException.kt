package com.possenti.post.exception

import java.lang.RuntimeException

class UserNotFoundException(override val message: String? = "User not found.", e: Throwable? = null) : RuntimeException(message, e)