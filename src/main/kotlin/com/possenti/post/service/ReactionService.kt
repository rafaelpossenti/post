package com.possenti.post.service

import com.possenti.post.document.Reaction
import com.possenti.post.dto.ReactionSaveDto
import com.possenti.post.repository.ReactionRepository
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ReactionService (val reactionRepository: ReactionRepository, val postService: PostService) {

    fun save(reactionSaveDto: ReactionSaveDto, postId: String, userId: String): Reaction {

        postService.findById(postId)

        val reaction = turnPostSaveDtoToPost(reactionSaveDto, postId, userId)
        return reactionRepository.save(reaction)
    }

    fun delete(reactionId: String) {
        this.findById(reactionId)
        reactionRepository.deleteById(reactionId)
    }

    fun findById(reactionId: String) = reactionRepository.findById(reactionId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Reaction inexistente.") }

    fun findByPostId(postId: String, pageRequest: PageRequest) = reactionRepository.findByPostId(postId, pageRequest)

    private fun turnPostSaveDtoToPost(reactionSaveDto: ReactionSaveDto, postId: String, userId: String): Reaction =
            Reaction(reactionSaveDto.reactionType, postId, userId, null)




}