package ru.bz.bzinforintegrationservice.domain.exception

class ResourceNotFoundException:  Exception {
    constructor(): super()
    constructor(message: String?): super(message)
}