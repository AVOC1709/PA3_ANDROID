package com.grupo1.gestionempleados.domain.repository

import com.grupo1.gestionempleados.domain.model.Empleado

/*
 * Contrato de las operaciones sobre empleados.
 * La capa domain solo conoce esta interfaz; la capa data la implementa.
 */
interface EmpleadoRepository {
    // Registra un empleado nuevo
    fun add(empleado: Empleado)

    // Devuelve todos los empleados
    fun getAll(): List<Empleado>

    // Reemplaza los datos del empleado que tenga el mismo DNI
    fun updateOne(empleado: Empleado)

    // Elimina al empleado con el DNI indicado
    fun delete(dni: String)

    // Busca un empleado por su DNI exacto (null si no existe)
    fun getByDni(dni: String): Empleado?

    // Busca empleados cuyo DNI, nombres o apellidos contengan el texto
    fun search(texto: String): List<Empleado>
}