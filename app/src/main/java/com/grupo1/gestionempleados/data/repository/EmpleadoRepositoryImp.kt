package com.grupo1.gestionempleados.data.repository

import com.grupo1.gestionempleados.data.datasource.EmpleadoDataSource
import com.grupo1.gestionempleados.domain.model.Empleado
import com.grupo1.gestionempleados.domain.repository.EmpleadoRepository

/*
 * Implementación del repositorio de empleados.
 * Trabaja sobre la lista en memoria de EmpleadoDataSource.
 */
class EmpleadoRepositoryImp : EmpleadoRepository {
    // Solo agrega si el DNI no está registrado (el DNI no debe repetirse)
    override fun add(empleado: Empleado) {
        val existe = EmpleadoDataSource.empleados.any { it.dni == empleado.dni }
        if (!existe) {
            EmpleadoDataSource.empleados.add(empleado)
        }
    }

    override fun getAll(): List<Empleado> {
        return EmpleadoDataSource.empleados
    }

    override fun updateOne(empleado: Empleado) {
        val index = EmpleadoDataSource.empleados.indexOfFirst { it.dni == empleado.dni }
        if (index != -1) {
            EmpleadoDataSource.empleados[index] = empleado
        }
    }

    override fun delete(dni: String) {
        EmpleadoDataSource.empleados.removeAll { it.dni == dni }
    }

    override fun getByDni(dni: String): Empleado? {
        return EmpleadoDataSource.empleados.find { it.dni == dni }
    }

    // La búsqueda no distingue mayúsculas de minúsculas
    override fun search(texto: String): List<Empleado> {
        return EmpleadoDataSource.empleados.filter {
            it.dni.contains(texto, ignoreCase = true) ||
                it.nombres.contains(texto, ignoreCase = true) ||
                it.apellidos.contains(texto, ignoreCase = true)
        }
    }
}