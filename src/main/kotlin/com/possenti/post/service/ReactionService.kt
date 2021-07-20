package com.possenti.post.service

import com.possenti.post.document.Reaction
import com.possenti.post.dto.ReactionSaveDto
import com.possenti.post.repository.ReactionRepository
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ReactionService(val reactionRepository: ReactionRepository, val postService: PostService) {

    fun save(reactionSaveDto: ReactionSaveDto, postId: String, userId: String): Reaction {

        postService.findById(postId)

        //TODO: Create a message to an kafka Topic, telling that a new reaction was made in his post with: userId/reactionType of who reacts

        val reaction = turnPostSaveDtoToPost(reactionSaveDto, postId, userId)
        return reactionRepository.save(reaction)
    }

    fun delete(reactionId: String) {
        this.findById(reactionId)
        reactionRepository.deleteById(reactionId)
    }

    fun findById(reactionId: String) = reactionRepository.findById(reactionId)
        .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Reaction inexistente.") }

    fun findByPostId(postId: String, pageable: Pageable) = reactionRepository.findByPostId(postId, pageable)

    private fun turnPostSaveDtoToPost(reactionSaveDto: ReactionSaveDto, postId: String, userId: String): Reaction =
        Reaction(reactionSaveDto.reactionType, postId, userId, null)


}