package com.possenti.post.service

import com.possenti.post.document.Reaction
import com.possenti.post.dto.ReactionSaveDto
import com.possenti.post.exception.ReactionNotFoundException
import com.possenti.post.repository.ReactionRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap

@Service
class ReactionService (val reactionRepository: ReactionRepository) {

    fun save(reactionSaveDto: ReactionSaveDto, postId: String, userId: String): Reaction {

        val reaction = turnPostSaveDtoToPost(reactionSaveDto, postId, userId)
        return reactionRepository.save(reaction)
    }

    fun delete(reactionId: String) {
        this.findById(reactionId)
        reactionRepository.deleteById(reactionId)
    }

    fun findById(reactionId: String) = reactionRepository.findById(reactionId).orElseThrow { ReactionNotFoundException() }

    fun findByPostId(postId: String, pageRequest: PageRequest) = reactionRepository.findByPostId(postId, pageRequest)

    private fun turnPostSaveDtoToPost(reactionSaveDto: ReactionSaveDto, postId: String, userId: String): Reaction =
            Reaction(reactionSaveDto.reactionType, postId, userId, null)




}