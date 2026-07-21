package ru.bz.bzinforintegrationservice.domain.common

import ru.bz.bzinforintegrationservice.domain.common.abortreason.AbortReason


sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Failure(
        val abortReason: AbortReason,
    ) : Result<Nothing>()
}