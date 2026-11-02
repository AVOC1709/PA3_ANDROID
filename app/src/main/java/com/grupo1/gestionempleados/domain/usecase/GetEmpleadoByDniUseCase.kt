package com.grupo1.gestionempleados.domain.usecase

import com.grupo1.gestionempleados.domain.model.Empleado
import com.grupo1.gestionempleados.domain.repository.EmpleadoRepository

// Caso de uso: obtener un empleado por su DNI (sirve para validar que no se repita)
class GetEmpleadoByDniUseCase(private val repository: EmpleadoRepository) {
    operator fun invoke(dni: String): Empleado? {
        return repository.getByDni(dni)
    }
}
