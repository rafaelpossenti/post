package com.possenti.post.dto

import javax.validation.constraints.NotEmpty


data class CommentSaveDto(
        @get:NotEmpty(message = "Texto não pode ser vazio.")
        val text: String
)
