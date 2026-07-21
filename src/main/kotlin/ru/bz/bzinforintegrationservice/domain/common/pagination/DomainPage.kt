package ru.bz.bzinforintegrationservice.domain.common.pagination


import kotlin.math.ceil


data class DomainPage<T>(
    val content: List<T>,
    val number: Int,
    val size: Int,
    val totalElements: Long
) {
    val totalPages: Int get() = if (size == 0) 0 else ceil(totalElements.toDouble() / size).toInt()
    val hasNext: Boolean get() = (number + 1) * size < totalElements
}
