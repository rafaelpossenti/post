package com.possenti.post.dto

import javax.validation.constraints.NotEmpty

data class PostSaveDto(
        @get:NotEmpty(message = "Id do usuário não pode ser vazio.")
        val userId: String? = null,

        @get:NotEmpty(message = "Texto não pode ser vazio.")
        val text: String? = null,
)
