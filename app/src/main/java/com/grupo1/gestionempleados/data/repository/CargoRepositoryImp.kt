package com.grupo1.gestionempleados.data.repository

import com.grupo1.gestionempleados.data.datasource.EmpleadoDataSource
import com.grupo1.gestionempleados.domain.model.Cargo
import com.grupo1.gestionempleados.domain.repository.CargoRepository

/*
 * Implementación del repositorio de cargos.
 * Trabaja sobre la lista en memoria de EmpleadoDataSource.
 */
class CargoRepositoryImp : CargoRepository {
    // Solo agrega si el código no está registrado
    override fun add(cargo: Cargo) {
        val existe = EmpleadoDataSource.cargos.any { it.codigo == cargo.codigo }
        if (!existe) {
            EmpleadoDataSource.cargos.add(cargo)
        }
    }

    override fun getAll(): List<Cargo> {
        return EmpleadoDataSource.cargos
    }

    // Además de actualizar el cargo, actualiza el nombre en los empleados que lo tienen
    override fun updateOne(cargo: Cargo) {
        val index = EmpleadoDataSource.cargos.indexOfFirst { it.codigo == cargo.codigo }
        if (index != -1) {
            EmpleadoDataSource.cargos[index] = cargo
            EmpleadoDataSource.empleados.forEachIndexed { i, empleado ->
                if (empleado.cargo.codigo == cargo.codigo) {
                    EmpleadoDataSource.empleados[i] = empleado.copy(cargo = cargo)
                }
            }
        }
    }

    override fun delete(codigo: String) {
        EmpleadoDataSource.cargos.removeAll { it.codigo == codigo }
    }

    override fun getByCodigo(codigo: String): Cargo? {
        return EmpleadoDataSource.cargos.find { it.codigo.equals(codigo, ignoreCase = true) }
    }

    // La búsqueda no distingue mayúsculas de minúsculas
    override fun search(texto: String): List<Cargo> {
        return EmpleadoDataSource.cargos.filter {
            it.codigo.contains(texto, ignoreCase = true) ||
                it.nombre.contains(texto, ignoreCase = true)
        }
    }

    override fun isInUse(codigo: String): Boolean {
        return EmpleadoDataSource.empleados.any { it.cargo.codigo == codigo }
    }
}
