package ru.bz.bzinforintegrationservice.domain.model.measuredremainder

enum class MeasuredRemainderStatus(val number: Int, val description: String) {
    CREATED(1, "������"),
    IN_COTZ(2, "� ����"),
    IN_MSCH(3, "� ������ ���"),
    UNDEFINED(0, "�� ������������");

    companion object {
        fun getByNumber(number: Int): MeasuredRemainderStatus = entries.firstOrNull{ it.number == number } ?: UNDEFINED
    }
}
