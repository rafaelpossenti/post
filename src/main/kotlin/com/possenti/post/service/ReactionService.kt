package com.possenti.post.service

import com.possenti.post.document.Reaction
import com.possenti.post.dto.ReactionSaveDto
import com.possenti.post.repository.ReactionRepository
import org.springframework.stereotype.Service

@Service
class ReactionService (val reactionRepository: ReactionRepository) {

    fun save(reactionSaveDto: ReactionSaveDto, postId: String, userId: String): Reaction {

        val reaction = turnPostSaveDtoToPost(reactionSaveDto, postId, userId)
        return reactionRepository.save(reaction)
    }

    private fun turnPostSaveDtoToPost(reactionSaveDto: ReactionSaveDto, postId: String, userId: String): Reaction =
            Reaction(reactionSaveDto.reactionType, postId, userId, null)
}