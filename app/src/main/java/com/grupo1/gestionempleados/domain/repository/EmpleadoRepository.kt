package com.grupo1.gestionempleados.domain.repository

import com.grupo1.gestionempleados.domain.model.Empleado

interface EmpleadoRepository {
    fun add(empleado: Empleado)
    fun getAll(): List<Empleado>
    fun updateOne(empleado: Empleado)
    fun delete(dni: String)
}