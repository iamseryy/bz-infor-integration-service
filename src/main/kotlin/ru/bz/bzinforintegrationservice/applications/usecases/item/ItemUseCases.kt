package ru.bz.bzinforintegrationservice.applications.usecases.item

import org.springframework.stereotype.Component


@Component
data class ItemUseCases(
    val getItemDetailByItemCode: UseCaseGetItemDetail
)