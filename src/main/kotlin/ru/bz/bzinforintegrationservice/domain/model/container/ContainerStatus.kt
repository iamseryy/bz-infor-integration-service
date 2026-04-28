package ru.bz.bzinforintegrationservice.domain.model.container

enum class ContainerStatus (val number: Int, val description: String)  {
    EMPTY(10, "������"),
    ON_CONFIG(20, "�� ������������"),
    CONFIGURED(30, "�������������"),
    FOR_PLACEMENT(40, "� ����������"),
    PLACED(50, "��������"),
    RESERVE(60, "������"),
    UNDEFINED(0, "�� ������������");

    companion object {
        fun getByNumber(number: Int): ContainerStatus = entries.firstOrNull{ it.number == number } ?: UNDEFINED
    }
}