package com.grupo1.gestionempleados.domain.repository

import com.grupo1.gestionempleados.domain.model.Cargo

/*
 * Contrato de las operaciones sobre cargos.
 * La capa domain solo conoce esta interfaz; la capa data la implementa.
 */
interface CargoRepository {
    // Registra un cargo nuevo
    fun add(cargo: Cargo)

    // Devuelve todos los cargos
    fun getAll(): List<Cargo>

    // Reemplaza los datos del cargo que tenga el mismo código
    fun updateOne(cargo: Cargo)

    // Elimina el cargo con el código indicado
    fun delete(codigo: String)

    // Busca un cargo por su código exacto (null si no existe)
    fun getByCodigo(codigo: String): Cargo?

    // Busca cargos cuyo código o nombre contengan el texto
    fun search(texto: String): List<Cargo>

    // Indica si algún empleado tiene asignado este cargo
    fun isInUse(codigo: String): Boolean
}
