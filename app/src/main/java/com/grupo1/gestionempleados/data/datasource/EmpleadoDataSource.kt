package com.grupo1.gestionempleados.data.datasource

import com.grupo1.gestionempleados.domain.model.Cargo
import com.grupo1.gestionempleados.domain.model.Empleado

object EmpleadoDataSource {
    /*
    * Lista de cargos
     */
    val cargos = listOf(
        Cargo(codigo = "C01", nombre = "Desarrollador FullStack"),
        Cargo(codigo = "C02", nombre = "Diseñador UI/UX"),
        Cargo(codigo = "C03", nombre = "Gerente de Proyectos"),
        Cargo(codigo = "C04", nombre = "Analista de Datos"),
        Cargo(codigo = "C05", nombre = "Administrador de BD"),
        Cargo(codigo = "C06", nombre = "Soporte Técnico"),
        Cargo(codigo = "C07", nombre = "Recursos Humanos")
    )

    /*
    * Lista de empleados
     */
    val empleados = mutableListOf(
        Empleado(
            dni = "08566261",
            nombres = "Mario",
            apellidos = "Bertotti",
            sexo = "Masculino",
            cargo = cargos[0],
            direccion = "Av. Lima 123",
            correo = "mario.bertotti@email.com"
        ),
        Empleado(
            dni = "72931032",
            nombres = "Carla Luisa",
            apellidos = "Mendoza Cisneros",
            sexo = "Femenino",
            cargo = cargos[1],
            direccion = "Calle Los Pinos 456",
            correo = "carla.mendoza@email.com"
        ),
        Empleado(
            dni = "09576213",
            nombres = "Rolando",
            apellidos = "Gomez A.",
            sexo = "Masculino",
            cargo = cargos[0],
            direccion = "Jr. Central 789",
            correo = "rolando.gomez@email.com"
        ),
        Empleado(
            dni = "07523134",
            nombres = "Roxana",
            apellidos = "Lopez Martinez",
            sexo = "Femenino",
            cargo = cargos[2],
            direccion = "Psje. Las Flores 321",
            correo = "roxana.lopez@email.com"
        ),
        Empleado(
            dni = "45123698",
            nombres = "Juan Carlos",
            apellidos = "Perez Quispe",
            sexo = "Masculino",
            cargo = cargos[3],
            direccion = "Av. Arequipa 987",
            correo = "juan.perez@email.com"
        ),
        Empleado(
            dni = "10234567",
            nombres = "Ana Maria",
            apellidos = "Torres Silva",
            sexo = "Femenino",
            cargo = cargos[4],
            direccion = "Calle Las Begonias 654",
            correo = "ana.torres@email.com"
        ),
        Empleado(
            dni = "09876543",
            nombres = "Luis Alberto",
            apellidos = "Ramirez Soto",
            sexo = "Masculino",
            cargo = cargos[5],
            direccion = "Jr. Huancavelica 111",
            correo = "luis.ramirez@email.com"
        ),
        Empleado(
            dni = "40123987",
            nombres = "Sofia",
            apellidos = "Castillo Morales",
            sexo = "Femenino",
            cargo = cargos[6],
            direccion = "Av. Javier Prado 222",
            correo = "sofia.castillo@email.com"
        ),
        Empleado(
            dni = "43216598",
            nombres = "Diego Alonso",
            apellidos = "Rojas Paredes",
            sexo = "Masculino",
            cargo = cargos[0],
            direccion = "Calle El Sol 333",
            correo = "diego.rojas@email.com"
        ),
        Empleado(
            dni = "07894561",
            nombres = "Lucia",
            apellidos = "Fernandez V.",
            sexo = "Femenino",
            cargo = cargos[1],
            direccion = "Av. Brasil 444",
            correo = "lucia.fernandez@email.com"
        ),
        Empleado(
            dni = "71234568",
            nombres = "Jorge Luis",
            apellidos = "Medina Cahuana",
            sexo = "Masculino",
            cargo = cargos[3],
            direccion = "Jr. Moquegua 555",
            correo = "jorge.medina@email.com"
        ),
        Empleado(
            dni = "08123459",
            nombres = "Carmen Rosa",
            apellidos = "Salazar Rios",
            sexo = "Femenino",
            cargo = cargos[4],
            direccion = "Av. Universitaria 666",
            correo = "carmen.salazar@email.com"
        ),
        Empleado(
            dni = "46789123",
            nombres = "Gabriel",
            apellidos = "Flores Nuñez",
            sexo = "Masculino",
            cargo = cargos[2],
            direccion = "Calle Luna Pizarro 777",
            correo = "gabriel.flores@email.com"
        ),
        Empleado(
            dni = "12345678",
            nombres = "Valeria",
            apellidos = "Vargas Machuca",
            sexo = "Femenino",
            cargo = cargos[5],
            direccion = "Av. Colonial 888",
            correo = "valeria.vargas@email.com"
        ),
        Empleado(
            dni = "87654321",
            nombres = "Renzo",
            apellidos = "Silva Santisteban",
            sexo = "Masculino",
            cargo = cargos[6],
            direccion = "Jr. Tacna 999",
            correo = "renzo.silva@email.com"
        ),
        Empleado(
            dni = "44556677",
            nombres = "Patricia",
            apellidos = "Guillen Tello",
            sexo = "Femenino",
            cargo = cargos[0],
            direccion = "Av. La Marina 101",
            correo = "patricia.guillen@email.com"
        ),
        Empleado(
            dni = "33445566",
            nombres = "Manuel",
            apellidos = "Espinoza Cardenas",
            sexo = "Masculino",
            cargo = cargos[1],
            direccion = "Calle Los Jazmines 202",
            correo = "manuel.espinoza@email.com"
        ),
        Empleado(
            dni = "22334455",
            nombres = "Claudia",
            apellidos = "Morales Benites",
            sexo = "Femenino",
            cargo = cargos[3],
            direccion = "Jr. Cuzco 303",
            correo = "claudia.morales@email.com"
        ),
        Empleado(
            dni = "11223344",
            nombres = "Fernando",
            apellidos = "Acosta Ñique",
            sexo = "Masculino",
            cargo = cargos[4],
            direccion = "Av. Angamos 404",
            correo = "fernando.acosta@email.com"
        ),
        Empleado(
            dni = "55667788",
            nombres = "Milagros",
            apellidos = "Chavez Campos",
            sexo = "Femenino",
            cargo = cargos[5],
            direccion = "Calle Los Rosales 505",
            correo = "milagros.chavez@email.com"
        ),
        Empleado(
            dni = "66778899",
            nombres = "Ricardo",
            apellidos = "Paredes Melendez",
            sexo = "Masculino",
            cargo = cargos[6],
            direccion = "Av. Benavides 606",
            correo = "ricardo.paredes@email.com"
        ),
        Empleado(
            dni = "77889900",
            nombres = "Andrea",
            apellidos = "Blanco Ruiz",
            sexo = "Femenino",
            cargo = cargos[0],
            direccion = "Jr. Puno 707",
            correo = "andrea.blanco@email.com"
        ),
        Empleado(
            dni = "99887766",
            nombres = "Enrique",
            apellidos = "Suarez Leon",
            sexo = "Masculino",
            cargo = cargos[1],
            direccion = "Av. Larco 808",
            correo = "enrique.suarez@email.com"
        ),
        Empleado(
            dni = "55443322",
            nombres = "Diana",
            apellidos = "Herrera Vasquez",
            sexo = "Femenino",
            cargo = cargos[2],
            direccion = "Calle Tarata 909",
            correo = "diana.herrera@email.com"
        ),
        Empleado(
            dni = "66554433",
            nombres = "Bruno",
            apellidos = "Alva Zavaleta",
            sexo = "Masculino",
            cargo = cargos[3],
            direccion = "Av. Brasil 1110",
            correo = "bruno.alva@email.com"
        ),
        Empleado(
            dni = "77665544",
            nombres = "Gabriela",
            apellidos = "Montero Diaz",
            sexo = "Femenino",
            cargo = cargos[4],
            direccion = "Jr. Lampa 1211",
            correo = "gabriela.montero@email.com"
        ),
        Empleado(
            dni = "88776655",
            nombres = "Alonso",
            apellidos = "Cornejo Quiroz",
            sexo = "Masculino",
            cargo = cargos[5],
            direccion = "Av. Abancay 1312",
            correo = "alonso.cornejo@email.com"
        ),
        Empleado(
            dni = "99001122",
            nombres = "Fiorella",
            apellidos = "Palacios Wong",
            sexo = "Femenino",
            cargo = cargos[6],
            direccion = "Calle Capon 1413",
            correo = "fiorella.palacios@email.com"
        ),
        Empleado(
            dni = "11002233",
            nombres = "Gonzalo",
            apellidos = "Vega Centeno",
            sexo = "Masculino",
            cargo = cargos[0],
            direccion = "Av. Petit Thouars 1514",
            correo = "gonzalo.vega@email.com"
        ),
        Empleado(
            dni = "22113344",
            nombres = "Katerin",
            apellidos = "Huaman Rojas",
            sexo = "Femenino",
            cargo = cargos[1],
            direccion = "Jr. Ascope 1615",
            correo = "katerin.huaman@email.com"
        )
    )
}