package com.grupo1.gestionempleados.domain.usecase

import com.grupo1.gestionempleados.domain.model.Cargo
import com.grupo1.gestionempleados.domain.repository.CargoRepository

// Caso de uso: buscar cargos por código o nombre
class SearchCargosUseCase(private val repository: CargoRepository) {
    operator fun invoke(texto: String): List<Cargo> {
        return repository.search(texto)
    }
}
