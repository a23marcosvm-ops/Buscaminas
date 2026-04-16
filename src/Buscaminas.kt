import kotlin.collections.contains
import kotlin.random.Random

//  2 excepciones, clase del estado, clase de celda
class sizeException(mensaje: String) : Exception(mensaje)
class difficultyException(mensaje: String) : Exception(mensaje)

enum class EstadoJuego { JUGANDO, GANADO, PERDIDO }

class Celda {
    var esMina = false
    var tieneBandera = false
    var minasAlrededor = 0

    // Sincronización de estados: si cambia uno, el otro se ajusta automáticamente
    var estaTapada: Boolean = true
        set(valor) {
            field = valor
            estaDestapada = !valor
        }

    var estaDestapada: Boolean = false
        set(valor) {
            field = valor
            estaTapada = !valor
        }
}

// --- CLASE PRINCIPAL ---
class Buscaminas(val lado: Int?, var dificultad: Int?) {
    var filas = lado
    var columnas = lado

    private val tablero: Array<Array<Celda>>
    var estado = EstadoJuego.JUGANDO
        private set

    init {
        if (lado !in 2..30 || lado == null) throw sizeException("Tamaño del lado no válido (2-30).")
        if (dificultad !in 1..3 || dificultad == null) throw difficultyException("Dificultad no válida (1-3).")

        this.filas = lado
        this.columnas = lado
        this.dificultad = dificultad

        this.tablero = Array(filas) { Array(columnas) { Celda() } }
        ponerMinas()
        calcularNumeros()
    }

    private fun ponerMinas() {
        val porcentaje = when (dificultad) {
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

    // --- INTERACCIONES ---

    fun destapar(f: Int, c: Int) {
        if (f !in 0 until filas || c !in 0 until columnas) return
        val celda = tablero[f][c]

        // Bloqueo: no se puede destapar si ya está abierta o tiene bandera
        if (celda.estaDestapada || celda.tieneBandera) return

        celda.estaDestapada = true
        if (celda.esMina) {
            estado = EstadoJuego.PERDIDO
        } else {
            verificarVictoria()
        }
    }

    fun flag(f: Int, c: Int) {
        if (f in 0 until filas && c in 0 until columnas) {
            val celda = tablero[f][c]
            // Solo se puede poner bandera si está tapada
            if (celda.estaTapada) {
                celda.tieneBandera = !celda.tieneBandera
            }
        }
    }

    /**
     * TRADUCTOR VISUAL REFACTORIZADO (Jerarquía de estados)
     */
    fun obtenerSimbolo(f: Int, c: Int): String {
        val celda = tablero[f][c]

        return if (celda.estaTapada) {
            // MUNDO TAPADO
            if (celda.tieneBandera) "P" else "■"
        } else {
            // MUNDO DESTAPADO
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
                // Si la celda NO es mina y sigue TAPADA, el jugador aún no ha terminado
                if (!celda.esMina && celda.estaTapada) {
                    celdasPorLimpiar++
                }
            }
        }

        // Si ya no quedan celdas seguras por abrir, ¡victoria!
        if (celdasPorLimpiar == 0) {
            estado = EstadoJuego.GANADO
        }
    }
}