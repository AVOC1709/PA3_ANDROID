package com.grupo1.gestionempleados.domain.model

// Modelo de dominio: empleado de la empresa (el DNI lo identifica y no se repite)
data class Empleado(
    val dni: String,
    val nombres: String,
    val apellidos: String,
    val sexo: String,
    val cargo: Cargo,
    val direccion: String,
    val correo: String
)
