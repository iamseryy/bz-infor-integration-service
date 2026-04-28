package ru.bz.bzinforintegrationservice.domain.usecases.item

import org.springframework.stereotype.Component


@Component
data class ItemUseCases(
    val getItemDetailByItemCode: UseCaseGetItemDetail
)