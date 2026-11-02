package com.grupo1.gestionempleados.domain.usecase

import com.grupo1.gestionempleados.domain.repository.CargoRepository

/*
 * Caso de uso: eliminar un cargo.
 * Devuelve false si algún empleado tiene ese cargo, para no dejarlo sin cargo.
 */
class DeleteCargoUseCase(private val repository: CargoRepository) {
    operator fun invoke(codigo: String): Boolean {
        if (repository.isInUse(codigo)) {
            return false
        }
        repository.delete(codigo)
        return true
    }
}
