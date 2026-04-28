package ru.bz.bzinforintegrationservice.infrastructure.repository.impl

import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.domain.repository.UserRepository


@Component
class UserRepositoryImpl (

): UserRepository{
    override fun findInforUserLoginByLogin(login: String): String {
        TODO("Not yet implemented")
    }
}