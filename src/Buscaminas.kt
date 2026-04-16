import kotlin.random.Random

// 2 excepciones, clase del estado, clase de celda
class sizeException(mensaje: String) : Exception(mensaje)
class difficultyException(mensaje: String) : Exception(mensaje)

enum class EstadoJuego { JUGANDO, GANADO, PERDIDO }

class Celda {
    var esMina = false
    var tieneBandera = false
    var minasAlrededor = 0

    var estaTapada = true
    var estaDestapada = false
        get() = !estaTapada
        set(valor) {
            field = valor
            estaTapada = !valor
        }

}

class Buscaminas(lado: Int?, dificultad: Int?) {
    val filas: Int
    val columnas: Int
    private val dific: Int
    private val tablero: Array<Array<Celda>>

    var estado = EstadoJuego.JUGANDO
        private set

    init {
        if (lado !in 2..30 || lado == null) throw sizeException("Tamaño del lado no válido (2-30)")
        if (dificultad !in 1..3 || dificultad == null) throw difficultyException("Dificultad no válida (1-3)")

        this.filas = lado!!
        this.columnas = lado
        this.dific = dificultad!!
        this.tablero = Array(filas) { Array(columnas) { Celda() } }

        ponerMinas()
        calcularNumeros()
    }

    private fun ponerMinas() {
        val porcentaje = when (dific) {
            1 -> 0.10
            2 -> 0.15
            else -> 0.20
        }
        val objetivoMinas = (filas * columnas * porcentaje).toInt().coerceAtLeast(1)

        var puestas = 0
        while (puestas < objetivoMinas) {
            val f = Random.nextInt(filas)
            val c = Random.nextInt(columnas)
            if (!tablero[f][c].esMina) {
                tablero[f][c].esMina = true
                puestas++
            }
        }
    }

    private fun calcularNumeros() {
        for (f in 0 until filas) {
            for (c in 0 until columnas) {
                if (!tablero[f][c].esMina) {
                    tablero[f][c].minasAlrededor = contarCercanas(f, c)
                }
            }
        }
    }

    private fun contarCercanas(f: Int, c: Int): Int {
        var minas = 0
        for (i in -1..1) {
            for (j in -1..1) {
                val nf = f + i
                val nc = c + j
                if (nf in 0 until filas && nc in 0 until columnas && tablero[nf][nc].esMina) {
                    minas++
                }
            }
        }
        return minas
    }

    fun destapar(f: Int, c: Int) {
        if (estado != EstadoJuego.JUGANDO || f !in 0 until filas || c !in 0 until columnas) return

        val celda = tablero[f][c]
        if (!celda.estaTapada || celda.tieneBandera) return

        celda.estaTapada = false

        if (celda.esMina) {
            estado = EstadoJuego.PERDIDO
            revelarTodo()
        } else {
            if (celda.minasAlrededor == 0) {
                // revelar recurisvamente cercanas
                for (i in -1..1) {
                    for (j in -1..1) {
                        destapar(f + i, c + j)
                    }
                }
            }
            verificarVictoria()
        }
    }

    private fun revelarTodo() {
        for (fila in tablero) {
            for (celda in fila) {
                if (celda.esMina) celda.estaDestapada = true
            }
        }
    }

    fun flag(f: Int, c: Int) {
        if (f in 0 until filas && c in 0 until columnas) {
            val celda = tablero[f][c]
            if (celda.estaTapada) {
                celda.tieneBandera = !celda.tieneBandera
            }
        }
    }

    fun obtenerSimbolo(f: Int, c: Int): String {
        val celda = tablero[f][c]
        return if (celda.estaTapada) {
            if (celda.tieneBandera) "P" else "■"
        } else {
            when {
                celda.esMina -> "X"
                celda.minasAlrededor == 0 -> " "
                else -> celda.minasAlrededor.toString()
            }
        }
    }

    private fun verificarVictoria() {
        var celdasPorLimpiar = 0
        for (filaCeldas in tablero) {
            for (celda in filaCeldas) {
                if (!celda.esMina && celda.estaTapada) {
                    celdasPorLimpiar++
                }
            }
        }
        if (celdasPorLimpiar == 0) estado = EstadoJuego.GANADO
    }
}