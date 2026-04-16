fun main() {
    println("--- NUEVA PARTIDA DE BUSCAMINAS ---")
    val juego = crearJuegoSeguro()

    buclePrincipal(juego)
    finalizarPartida(juego)
}



fun crearJuegoSeguro(): Buscaminas {
        return try {
            println("Tablero n x n. Introduce el tamaño del lado (mínimo 2):")
            val size = readln().toIntOrNull()
            println("Selecciona el nivel de dificultad:")
            println("[1] Fácil. 10% de minas\n[2] Estándar. 15% de minas\n[3] Difícil. 20% de minas")
            val difficulty = readln().toIntOrNull()

            Buscaminas(size, difficulty)

        // si ocurre alguna excepcion, me la devuelve, e intento crear tablero de nuevo
        } catch (e: sizeException) {
            println("Error: ${e.message}")
            crearJuegoSeguro()

        } catch (e: difficultyException) {
            println("Error: ${e.message}")
            crearJuegoSeguro()
        }
}


fun buclePrincipal(juego: Buscaminas) {
    while (juego.estado == EstadoJuego.JUGANDO) {
        mostrarTablero(juego)
        menuOpciones(juego)
    }
}

fun mostrarTablero(juego: Buscaminas) {
    println("\n   " + (0 until juego.columnas).joinToString("  "))
    for (f in 0 until juego.filas) {
        print("$f |")
        for (c in 0 until juego.columnas) {
            print("[${juego.obtenerSimbolo(f, c)}]")
        }
        println()
    }
}

fun menuOpciones(juego: Buscaminas) {
    var opcionValida = false

    println("\nEscoge que deseas hacer")
    println("[1] Destapar celda\n[2] Poner bandera\n[3] Mostrar tablero\n[4] Mostrar estado del juego")
    var opcion = readln().toIntOrNull()

    while (!opcionValida) {
        when (opcion) {
            1 -> {
                println("--- DESTAPAR ---")
                println("Introduce la fila(y):")
                val f = pedirEntero()
                println("Introduce la columna(x):")
                val c = pedirEntero()
                juego.destapar(f, c)
                opcionValida = true
            }
            2 -> {
                println("--- BANDERA ---")
                println("Introduce la fila(y):")
                val f = pedirEntero()
                println("Introduce la columna(x):")
                val c = pedirEntero()
                juego.flag(f, c)
                opcionValida = true
            }
            3 -> {
                mostrarTablero(juego)
                opcionValida = true
            }
            4 -> {
                println("Estado actual: ${juego.estado}")
                opcionValida = true
            }
            else -> {
                println("Opción no válida, introduce un número válido.")
                println("[1] Destapar celda\n[2] Poner bandera\n[3] Mostrar tablero\n[4] Mostrar estado del juego")
                opcion = readln().toIntOrNull()
            }
        }
    }
}

fun pedirEntero(): Int {
    var num = readln().toIntOrNull()

    while (num == null || num !in 2..30) {
        println("Introduce un número válido (2..30):")
        num = readln().toIntOrNull()
    }
    return num
}

fun finalizarPartida(juego: Buscaminas) {
    mostrarTablero(juego)
    if (juego.estado == EstadoJuego.GANADO) println("¡VICTORIA!")
    else println("¡BOOM! Juego terminado.")
}











