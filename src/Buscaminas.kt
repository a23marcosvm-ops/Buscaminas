import kotlin.collections.contains
import kotlin.random.Random


fun empezarPartida(){

}

class Buscaminas(){

    /*  leyenda valores celda
        -1 -> sin destap
        1 -> destapada mina   --------> explosion y perder partida
        2 -> destapada vacia
        3 -> sin destapar     with flag
     */

    //tablero vacio
    var size = 0
    var celdasTotales = 0
    var cantidadMinasActuales = 0     //  cantidad de minas debe ser configurable


    var tablero = Array<IntArray>(0){ IntArray(0){0} }


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
    fun ponerMinasSegunDificultad(input: String){
        var n = input.toIntOrNull()

        while (n !in 1..3){
            println("Escoge una opcion valida")
            n = readln().toIntOrNull()
        }
        when(n) {
            1 -> {

                val cantidadMinas = (n*n* 0.10).toInt().coerceAtLeast(1)
                while (cantidadMinasActuales < cantidadMinas) {
                    val fila = Random.nextInt(n)
                    val columna = Random.nextInt(n)

                    if (tablero[fila][columna] != -1) {
                        tablero[fila][columna] = -1
                        cantidadMinasActuales++
                        if (cantidadMinasActuales >= celdasTotales){  throw Exception("El número de minas es igual o mayor que el número de celdas")  }
                    }
                }


            }
            2 -> {

                val cantidadMinas = (n * n * 0.15).toInt().coerceAtLeast(1)
                while (cantidadMinasActuales < cantidadMinas) {
                    val fila = Random.nextInt(n)
                    val columna = Random.nextInt(n)

                    if (tablero[fila][columna] != -1) {
                        tablero[fila][columna] = -1
                        cantidadMinasActuales++
                        if (cantidadMinasActuales >= celdasTotales) {
                            throw Exception("El número de minas es igual o mayor que el número de celdas")
                        }
                    }
                }
            }
            3 -> {

                val cantidadMinas = (n*n* 0.2).toInt().coerceAtLeast(1)
                while (cantidadMinasActuales < cantidadMinas) {
                    val fila = Random.nextInt(n)
                    val columna = Random.nextInt(n)

                    if (tablero[fila][columna] != -1) {
                        tablero[fila][columna] = -1
                        cantidadMinasActuales++
                        if (cantidadMinasActuales >= celdasTotales) {
                            throw Exception("El número de minas es igual o mayor que el número de celdas")
                        }
                    }
                }


            }
        }


        ////Si el número de minas es igual o mayor que el número de celdas del tablero debe enviar excepción

    }

    }





    //  logica gestion tablero


    //completar
    fun mostrar(){
        for (intArray in tablero){
            println(intArray.joinToString(" "))
        }
    }



    //completar
    fun destapar(fila: Int, col: Int){

    }

    //completar
    fun flag(fila: Int, col: Int){

    }


    fun opciones(){
        println("Que deseas hacer?")
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


