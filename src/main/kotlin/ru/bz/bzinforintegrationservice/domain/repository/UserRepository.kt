package ru.bz.bzinforintegrationservice.domain.repository

interface UserRepository {
    fun findInforUserLoginByLogin(login: String):String?
}