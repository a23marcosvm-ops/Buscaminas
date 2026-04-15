fun main(){

    println("---NUEVA PARTIDA DE BUSCAMINAS---")
    print("Introduce el valor de ancho/alto: ")

    crearTablero()

    println("\nSelecciona el nivel de dificultad:")
    println("[1] Fácil. 10% de minas\n[2] Estándar. 15% de minas\n[3] Difícil. 20% de minas")


    seleccionarDificultad()
    empezarPartida()




}

var l = Buscaminas()


fun crearTablero(){
    // creo el tablero usando un metodo, el cual contiene un if..throw
    try {
        l.crearTableroDeTamano(readln())
    }catch (e: Exception){
        println("Error: ${e.message}")
    }
}

fun seleccionarDificultad(){
    try {
        l.ponerMinasSegunDificultad(readln())
    }catch (e: Exception){
        println("Error: ${e.message}")
    }
}