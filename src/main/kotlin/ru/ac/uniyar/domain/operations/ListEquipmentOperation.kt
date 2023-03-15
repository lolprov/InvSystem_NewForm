package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.EMPTY_UUID
import ru.ac.uniyar.domain.storage.Equipment
import ru.ac.uniyar.domain.storage.EquipmentRepository
import java.util.*

interface ListEquipmentOperation {
    fun list(): List<Equipment>
    fun add(device: Equipment)
    fun update(device: Equipment)

}

class ListEquipmentOperationImplementation(
    private val equipmentRepository: EquipmentRepository,
) : ListEquipmentOperation {
    override fun list() = equipmentRepository.list()

    override fun add(device:Equipment) {
        equipmentRepository.add(device)
    }

    override fun update(device: Equipment) {
        equipmentRepository.update(device)
    }




}
