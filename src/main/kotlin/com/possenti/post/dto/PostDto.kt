package com.possenti.post.dto

import javax.validation.constraints.NotEmpty

data class PostDto(
        @get:NotEmpty(message = "Texto não pode ser vazio.")
        val text: String? = null,

        @get:NotEmpty(message = "Usuário não pode ser vazio.")
        var userId: String? = null,

        var id: String? = null
)
