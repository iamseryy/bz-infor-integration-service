package ru.bz.bzinforintegrationservice.applications.usecases.measuredremainder

import org.springframework.stereotype.Service

@Service
data class MeasuredRemainderUseCases(
    val search: SearchMeasuredRemaindersUseCase,
    val update: UpdateMeasuredRemaindersUseCase,
    val getLocationsByWarehouseCode: GetLocationsByWarehouseCodeUseCase,
    val getMaterials: GetMaterialsUseCase,
    val getProjectCodes: GetProjectCodesUseCase,
    val getWarehouseCodes: GetWarehouseCodesUseCase
)