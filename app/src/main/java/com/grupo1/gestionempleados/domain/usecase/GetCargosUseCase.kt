package com.grupo1.gestionempleados.domain.usecase

import com.grupo1.gestionempleados.domain.model.Cargo
import com.grupo1.gestionempleados.domain.repository.CargoRepository

// Caso de uso: listar todos los cargos
class GetCargosUseCase(private val repository: CargoRepository) {
    operator fun invoke(): List<Cargo> {
        return repository.getAll()
    }
}
