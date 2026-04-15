import kotlin.collections.contains
import kotlin.random.Random


fun empezarPartida(){

}

class Buscaminas(){

    /* LEYENDA
            tablero visual:
                '[ ]'           tapada
                '[P]'          Tiene bandera
                '[X]'          mina destapada
                '[■]'          sin mina destapada. muestranumero minas alrededor

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
            if (n!! < 1){ throw Exception("El tamaño del tablero no puede ser inferior a 2x2") }

            println("Escoge una opcion valida")
            n = readln().toIntOrNull()
        }
        tablero = Array<IntArray>(n){ IntArray(n){0} }
        size = n
        celdasTotales = n*n
    }

    //logica creacion y colocacion de minas
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


    fun mostrar(){
        for (fila in l.tableroVisual){
            println(fila.joinToString(" "){char -> "[$char]"})
        }
        opciones()
    }



    //completar
    fun destapar(fila: Int, col: Int){

    }

    //completar
    fun flag(fila: Int, col: Int){

    }


    fun opciones(){
        println("\nQue deseas hacer?")
        println("[1] Destapar celda\n[2] Poner bandera\n[3] Mostrar tablero")
        opcionEscogida()
    }




}


//  consultar información de sólo lectura como por ejemplo saber el estado del juego (finalizado o no, etc.)
fun estado(){

}


fun opcionEscogida(){
    var n = readln()
    var validInput = arrayOf("1","2","3","4")

    while (n !in validInput){
        println("Escoge una opcion valida")
        n = readln()
    }

    when(n.toInt()){
        1 -> Buscaminas.destapar()
        2 -> Buscaminas.flag()
        3 -> Buscaminas.mostrar()
        4 -> estado()
    }
}


