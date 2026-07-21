package ru.bz.bzinforintegrationservice.domain.common.pagination

data class DomainPageRequest(
    val number: Int,
    val size: Int
) {
    val offset: Int get() = number * size
}