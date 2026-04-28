package ru.bz.bzinforintegrationservice.domain.usecases.user

import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.domain.repository.UserRepository


@Component
class FindInforUserLoginByLoginUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(login: String): String? = userRepository.findInforUserLoginByLogin(login)
}