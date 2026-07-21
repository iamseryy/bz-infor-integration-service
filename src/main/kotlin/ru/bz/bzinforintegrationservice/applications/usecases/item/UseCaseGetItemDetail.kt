package ru.bz.bzinforintegrationservice.applications.usecases.item

import org.springframework.stereotype.Component
import ru.bz.bzinforintegrationservice.domain.repository.ItemRepository


@Component
class UseCaseGetItemDetail(
    private val itemRepository: ItemRepository
) {
    operator fun invoke(itemCode: String) = itemRepository.getItemDetail(itemCode)
}