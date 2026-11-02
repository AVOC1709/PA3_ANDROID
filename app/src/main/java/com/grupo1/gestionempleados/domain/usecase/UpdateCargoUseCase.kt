package com.grupo1.gestionempleados.domain.usecase

import com.grupo1.gestionempleados.domain.model.Cargo
import com.grupo1.gestionempleados.domain.repository.CargoRepository

// Caso de uso: modificar el nombre de un cargo existente
class UpdateCargoUseCase(private val repository: CargoRepository) {
    operator fun invoke(cargo: Cargo) {
        repository.updateOne(cargo)
    }
}
