package com.grupo1.gestionempleados.domain.usecase

import com.grupo1.gestionempleados.domain.model.Cargo
import com.grupo1.gestionempleados.domain.repository.CargoRepository

/*
 * Caso de uso: registrar un cargo.
 * Devuelve false si el código ya existe, porque no debe repetirse.
 */
class AddCargoUseCase(private val repository: CargoRepository) {
    operator fun invoke(cargo: Cargo): Boolean {
        if (repository.getByCodigo(cargo.codigo) != null) {
            return false
        }
        repository.add(cargo)
        return true
    }
}
