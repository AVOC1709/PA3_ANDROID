package com.grupo1.gestionempleados.domain.model

data class Empleado(
    val dni: Number,
    val nombres: String,
    val apellidos: String,
    val sexo: String,
    val cargo: Cargo,
    val direccion: String,
    val correo: String
)
