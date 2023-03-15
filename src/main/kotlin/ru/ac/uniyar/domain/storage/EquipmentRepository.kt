package ru.ac.uniyar.domain.storage

import java.util.UUID

class EquipmentRepository(equipmentList: List<Equipment>) {
    private val equipment = equipmentList.associateBy { it.id }.toMutableMap()

    fun list() = equipment.values.toList()

    fun fetch(id: UUID) = equipment[id]
    fun add(device: Equipment) {
        var newId = device.id
        while (equipment.containsKey(newId) || newId== EMPTY_UUID){
            newId= UUID.randomUUID()
        }
        equipment[newId]=device.setUuid(newId)
    }
    fun update(device: Equipment){
        val id=device.id
        equipment[id]= device
    }


}
