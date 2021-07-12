package com.possenti.post.component

import com.possenti.post.client.UserClient
import com.possenti.post.exception.UserNotFoundException
import org.springframework.stereotype.Component

@Component
class UserComponent(val userClient: UserClient) {

    fun validateWhetherUserExists(userId: String) {
        try {
            userClient.exists(userId)
        } catch (ex: Throwable) {
            throw UserNotFoundException()
        }
//
//        kotlin.runCatching { userClient.exists(userId) }
//                .onFailure { throw UserNotFoundException() }
    }
}