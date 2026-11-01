package com.grupo1.gestionempleados.data.repository

import com.grupo1.gestionempleados.data.datasource.EmpleadoDataSource
import com.grupo1.gestionempleados.domain.model.Empleado
import com.grupo1.gestionempleados.domain.repository.EmpleadoRepository

class EmpleadoRepositoryImp : EmpleadoRepository {
    override fun add(empleado: Empleado) {
        val existe = EmpleadoDataSource.empleados.any { it.dni == empleado.dni }
        if (!existe) {
            EmpleadoDataSource.empleados.add(empleado)
        }
    }

    override fun getEmpleados(): List<Empleado> {
    }
}