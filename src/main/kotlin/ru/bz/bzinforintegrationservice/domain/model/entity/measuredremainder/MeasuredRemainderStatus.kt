package ru.bz.bzinforintegrationservice.domain.model.entity.measuredremainder

enum class MeasuredRemainderStatus(val number: Int, val description: String) {
    CREATED(1, "Введен"),
    IN_COTZ(2, "В КОЦе"),
    IN_MSCH(3, "В работе МСЧ"),
    USED(4, "Использован"),
    ON_CONSIGNMENT(5, "На реализации"),
    DELETED(100, "Удален"),
    UNDEFINED(0, "Не определено");

    companion object {
        private val byNumberMap: Map<Int, MeasuredRemainderStatus> =
            entries.associateBy { it.number }

        fun fromNumber(number: Int): MeasuredRemainderStatus? = byNumberMap[number]
        fun fromNumberOrDefault(number: Int): MeasuredRemainderStatus = byNumberMap[number] ?: UNDEFINED
    }
}