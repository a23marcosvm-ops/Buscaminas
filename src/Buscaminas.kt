import com.sun.jdi.Type

class Buscaminas(){

    /*  leyenda valores celda
        0 -> vacia
        1 -> mina
        2 -> mina + flag????   ni idea de si luego lo pondre asi
     */

    //tablero vacio
    var size = 0
    var cantidadMinas = 0
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


    fun opciones(n: Int){

    }


}



fun estado(){

}



fun iniciarLogica(){

}



//  otras clases y/o funciones si lo consideras oportuno.


//  dimensiones del tablero y la cantidad de minas debe ser configurable.

//clase Buscaminas debe enviar excepción:
//Si el número de minas es igual o mayor que el número de celdas del tablero debe enviar excepción