class Buscaminas(){

    /*  leyenda valores celda
        0 -> vacia
        1 -> mina
        2 -> mina + flag????   ni idea de si luego lo pondre asi
     */

    //tablero vacio
    var size = 0
    var cantidadMinas = 0     //  cantidad de minas debe ser configurable
    //  //clase Buscaminas debe enviar excepción:
    ////Si el número de minas es igual o mayor que el número de celdas del tablero debe enviar excepción
    var tablero = Array<IntArray>(0){ IntArray(0){0} }


    //  logica creacion tablero
    fun crearTableroDeTamano(n: Int){
        if (n < 1){ throw Exception("El tamño del tablero no puede ser inferior a 2x2") }
        tablero = Array<IntArray>(n){ IntArray(n){0} }
        size = n

        //completar para que ponga minas


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
        println("[1] Mostrar tablero\n[2] Destapar celda\n[3] Poner bandera")
        opcionEscogida()
    }




}


//  consultar información de sólo lectura como por ejemplo saber el estado del juego (finalizado o no, etc.)
fun estado(){

}


fun opcionEscogida(){
    var n = readln().toInt()
    if (n !in arrayOf(1,2,3,4)){
        println("Escoge una opcion valida")
        n = readln().toInt()
    }
    when(n){
        1 -> Buscaminas.mostrar()
        2 -> destapar()
        3 -> flag()
        4 -> estado()
    }

}



//  ????
fun iniciarLogica(){

}