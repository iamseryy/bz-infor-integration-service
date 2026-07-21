package ru.bz.bzinforintegrationservice.domain.model.container

enum class ContainerStatus (val number: Int, val description: String)  {
    EMPTY(10, "Пустой"),
    ON_CONFIG(20, "На комплектации"),
    CONFIGURED(30, "Укомплектован"),
    FOR_PLACEMENT(40, "К размещению"),
    PLACED(50, "Размещен"),
    RESERVE(60, "Резерв"),
    UNDEFINED(0, "Не определено");

    companion object {
        fun getByNumber(number: Int): ContainerStatus = entries.firstOrNull{ it.number == number } ?: UNDEFINED
    }
}