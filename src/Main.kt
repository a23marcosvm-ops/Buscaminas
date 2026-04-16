import kotlin.system.exitProcess

fun main() {
    println("--- NUEVA PARTIDA DE BUSCAMINAS ---")
    val juego = crearJuegoSeguro()

    buclePrincipal(juego)
    finalizarPartida(juego)
}

annotation class mainEnd

fun crearJuegoSeguro(): Buscaminas {
    while (true) {
        try {
            println("Tablero n x n. Introduce el tamaño del lado (2-30):")
            val size = readln().toIntOrNull()
            println("Selecciona el nivel de dificultad:")
            println("[1] Fácil. 10% de minas\n[2] Estándar. 15% de minas\n[3] Difícil. 20% de minas")
            val difficulty = readln().toIntOrNull()

            return Buscaminas(size, difficulty)

        }catch (e: sizeException) {
            println("Error: ${e.message}")
            crearJuegoSeguro()
        } catch (e: difficultyException) {
            println("Error: ${e.message}")
            crearJuegoSeguro()
        }
    }
}

var lastOptionWasStatus = false
var lastOptionWasShow = false

fun buclePrincipal(juego: Buscaminas) {
    while (juego.estado == EstadoJuego.JUGANDO) {
        println()
        if (!lastOptionWasStatus && !lastOptionWasShow) {
            mostrarTablero(juego)
        }
        lastOptionWasStatus = false
        lastOptionWasShow = false
        menuOpciones(juego)
    }
}

fun mostrarTablero(juego: Buscaminas) {
    println("    " + (0 until juego.columnas).joinToString("  "))
    for (f in 0 until juego.filas) {
        print("$f  ")
        for (c in 0 until juego.columnas) {
            print("[${juego.obtenerSimbolo(f, c)}]")
        }
        println()
    }
}

fun menuOpciones(juego: Buscaminas) {
    var opcionValida = false

    println("\nEscoge que deseas hacer")
    println("[1] Destapar celda\n[2] Poner bandera\n[3] Mostrar tablero\n[4] Mostrar estado del juego\n[5] Cerrar Buscaminas")
    var opcion = readln().toIntOrNull()

    while (!opcionValida) {
        when (opcion) {
            1 -> {
                println("--- DESTAPAR ---")
                println("Introduce la fila(y):")
                val f = pedirEntero(juego.filas)
                println("Introduce la columna(x):")
                val c = pedirEntero(juego.columnas)
                juego.destapar(f, c)
                opcionValida = true
            }
            2 -> {
                println("--- BANDERA ---")
                println("Introduce la fila(y):")
                val f = pedirEntero(juego.filas)
                println("Introduce la columna(x):")
                val c = pedirEntero(juego.columnas)
                juego.flag(f, c)
                opcionValida = true
            }
            3 -> {
                mostrarTablero(juego)
                lastOptionWasShow = true
                opcionValida = true
            }
            4 -> {
                println("Estado actual: ${juego.estado}")
                lastOptionWasStatus = true
                opcionValida = true
            }
            5 -> {
                println("\n\n\n ADIOS MUNDO CRUEL\nADIOS MUNDO CRUEL\nADIOS MUNDO CRUEL")
                exitProcess(0)
            }
            else -> {
                println("Opción no válida, introduce un número válido.")
                println("[1] Destapar celda\n[2] Poner bandera\n[3] Mostrar tablero\n[4] Mostrar estado del juego\n[5] Cerrar Buscaminas")
                opcion = readln().toIntOrNull()
            }
        }
    }
}

fun pedirEntero(limite: Int): Int {
    var num = readln().toIntOrNull()
    while (num == null || num !in 0 until limite) {
        println("Introduce un número válido (0..${limite - 1}):")
        num = readln().toIntOrNull()
    }
    return num!!
}

fun finalizarPartida(juego: Buscaminas) {
    mostrarTablero(juego)
    if (juego.estado == EstadoJuego.GANADO) println("¡VICTORIA!")
    else println("DERROTA.")
}