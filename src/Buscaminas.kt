import kotlin.collections.contains
import kotlin.random.Random

class sizeException(mensaje: String) : Exception(mensaje)
class difficultyException(mensaje: String) : Exception(mensaje)

enum class EstadoJuego {
    JUGANDO,
    GANADO,
    PERDIDO
}

class Celda {
    var esMina = false
    var estaDestapada = false
    var tieneBandera = false
    var minasAlrededor = 0
}






class Buscaminas(val lado: Int, val dificultad: Int) {
    val filas = lado
    val columnas = lado

    private val tablero = Array(filas) { Array(columnas) { Celda() } }

    var estado = EstadoJuego.JUGANDO
        private set

    init {
        // ver
        if (lado < 2) throw sizeException("El tablero debe ser al menos de 2x2")
        if (dificultad !in 1..3) throw difficultyException("Dificultad no válida")

        // 3. Ejecutamos la configuración inicial
        ponerMinas()
        calcularNumeros()
    }

    private fun ponerMinas() {
        val porcentaje = when (dificultad) {
            1 -> 0.10
            2 -> 0.15
            else -> 0.20
        }
        val totalCeldas = filas * columnas
        val objetivoMinas = (totalCeldas * porcentaje).toInt().coerceAtLeast(1)

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

    // --- MÉTODOS DE INTERACCIÓN (Usados por menuOpciones) ---

    fun destapar(f: Int, c: Int) {
        if (f !in 0 until filas || c !in 0 until columnas) return
        val celda = tablero[f][c]
        if (celda.estaDestapada || celda.tieneBandera) return

        celda.estaDestapada = true
        if (celda.esMina) {
            estado = EstadoJuego.PERDIDO
        } else {
            verificarVictoria()
        }
    }

    fun flag(f: Int, c: Int) {
        if (f in 0 until filas && c in 0 until columnas && !tablero[f][c].estaDestapada) {
            tablero[f][c].tieneBandera = !tablero[f][c].tieneBandera
        }
    }

    fun obtenerSimbolo(f: Int, c: Int): String {
        val celda = tablero[f][c]
        return when {
            celda.tieneBandera -> "P"
            !celda.estaDestapada -> " "
            celda.esMina -> "X"
            else -> if (celda.minasAlrededor == 0) "■" else celda.minasAlrededor.toString()
        }
    }

    private fun verificarVictoria() {
        var ganaste = true
        for (filaCeldas in tablero) {
            for (celda in filaCeldas) {
                if (!celda.esMina && !celda.estaDestapada) {
                    ganaste = false
                    break
                }
            }
        }
        if (ganaste) estado = EstadoJuego.GANADO
    }
}

















fun empezarPartida(){

}

class Buscaminas(){

    /* LEYENDA
            tablero visual:
                '[ ]'          tapada
                '[P]'          tapada bandera
                '[X]'          destapada mina
                '[■]'          destapada vacia
                                    si tiene mina cerca -> muestranumero minas alrededor

            tablero logico:



     */

    //tablero vacio
    var size = 0
    var celdasTotales = 0
    var cantidadMinasActuales = 0     //  cantidad de minas debe ser configurable


    var tablero = Array<IntArray>(0){ IntArray(0){0} }

    var tableroVisual = Array(0) { CharArray(0) { ' ' } }
    var tableroLogico = Array(0) { IntArray(0) { 0 } }



    //  logica creacion tablero
    fun crearTableroDeTamano(input: String){
        var n = input.toIntOrNull()

        while (n !in 2..30 || n == null ){
            if (n!! < 1){ throw Exception("El tamaño del tablero no puede ser inferior a 2x2\nEscoge una opción válida (número entre 2 y 30):") }

            println("Escoge una opcion valida")
            n = readln().toIntOrNull()
        }
        tablero = Array<IntArray>(n){ IntArray(n){0} }
        size = n
        celdasTotales = n*n
    }

    //logica colocacion de minas
    fun ponerMinasSegunDificultad(input: String) {
        var dificultad = input.toIntOrNull()

        while (dificultad !in 1..3 || dificultad == null) {
            println("Dificultad inválida (1, 2 o 3). Escoge una:")
            dificultad = readln().toIntOrNull()
        }
        val porcentaje = when (dificultad) {
            1 -> 0.10
            2 -> 0.15
            else -> 0.20
        }

        val objetivoMinas = (celdasTotales * porcentaje).toInt().coerceAtLeast(1)
        while (cantidadMinasActuales < objetivoMinas) {
            val fila = Random.nextInt(size)
            val columna = Random.nextInt(size)

            if (tableroLogico[fila][columna] != -1) {
                tableroLogico[fila][columna] = -1
                cantidadMinasActuales++

                if (cantidadMinasActuales >= celdasTotales) {  throw Exception("El número de minas es igual o mayor que el número de celdas")   }
            }
        }
    }
}





    //  logica gestion tablero






    //completar
    fun destapar(y: Int, x: Int){



        opciones()
    }

    //completar
    fun flag(y: Int, x: Int){

        opciones()
    }

    fun mostrar(){
        for (fila in l.tableroVisual){
            println(fila.joinToString(" "){char -> "[$char]"})
        }
        opciones()
    }


    fun opciones(){
        println("\nQue deseas hacer?")
        println("[1] Destapar celda\n[2] Poner bandera\n[3] Mostrar tablero\n[4] Mostrar estado del juego")
        opcionEscogida()
    }




}


//  consultar información de sólo lectura como por ejemplo saber el estado del juego (finalizado o no, etc.)
fun estado(){

    opciones()
}


fun opcionEscogida(){
    var n = readln()
    var validInput = arrayOf("1","2","3","4")

    while (n !in validInput){
        println("Escoge una opcion valida:")
        println("[1] Destapar celda\n[2] Poner bandera\n[3] Mostrar tablero\n[4] Mostrar estado del juego")
        n = readln()
    }

    when(n.toInt()){
        1 -> Buscaminas.destapar()
        2 -> Buscaminas.flag()
        3 -> Buscaminas.mostrar()
        4 -> estado()
    }
}


