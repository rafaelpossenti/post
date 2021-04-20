package com.possenti.post.dtos

import com.possenti.post.enum.ReactionTypeEnum
import javax.validation.constraints.NotEmpty

data class ReactionSaveDto(
        val reactionType: ReactionTypeEnum,
)
