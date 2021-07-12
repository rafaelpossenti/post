package com.possenti.post.exception

import java.lang.RuntimeException

class ReactionNotFoundException(override val message: String? = "Reaction not found.", e: Throwable? = null) : RuntimeException(message, e)