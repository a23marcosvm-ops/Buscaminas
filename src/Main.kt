fun main(){
    var l = Buscaminas()        // instancio la clase de buscaminas vacia(la logica)

    println("---NUEVA PARTIDA DE BUSCAMINAS---")
    print("Introduce el valor de ancho/alto: ")

    //  pido size, para pasarselo al metodo de crear tablero
    var size = readln().toInt()
    try {
        l.crearTableroDeTamano(size)    // creo el tablero usando un metodo, el cual contiene un if..throw

    }catch (e: Exception){
        println("Error: ${e.message}")
    }


    //  mirar spot correcto
   // iniciarLogica() ???

    //  interaccon la logica usando metodos de buscaminas.kt


    //  consultar información de sólo lectura como por ejemplo saber el estado del juego (finalizado o no, etc.)

    //Main.kt sólo se puede modificar la información a través de las funciones públicas que ofrezcan las clases de Buscaminas.kt

    // Desde main solo se puede modificar el tablero con la función pública de destapar



}