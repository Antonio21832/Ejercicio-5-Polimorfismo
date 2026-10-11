//Estuardo Antonio Molina 26878
import java.util.ArrayList;
//Clase que controla las opciones del menu y conecta la vista con la mision
public class ControladorMision {
    //Objetos que permiten utilizar el modelo y la vista
    private Mision mision;
    private VistaConsola vista;
    //Constructor que recibe la mision y la vista
    public ControladorMision(Mision mision, VistaConsola vista) {
        this.mision = mision;
        this.vista = vista;
    }
    //Metodo que mantiene el menu activo hasta seleccionar salir
    public void iniciar() {
        //Variable que almacena la opcion seleccionada
        int opcion = 0;
        //Ciclo que mantiene el programa funcionando
        while (opcion != 6) {
            //Muestra el menu principal
            vista.mostrarMenu();
            //Solicita la opcion al usuario
            opcion = vista.leerOpcion();
            //Verifica la opcion seleccionada
            if (opcion == 1) {
                listarModulos();
            }
            else if (opcion == 2) {
                buscarModulo();
            }
            else if (opcion == 3) {
                mostrarCatalogo();
            }
            else if (opcion == 4) {
                avanzarCiclo();
            }
            else if (opcion == 5) {
                mostrarComunicaciones();
            }
            else if (opcion == 6) {
                vista.mostrarMensaje("Programa finalizado.");
            }
            else {
                vista.mostrarMensaje("Opcion no valida.");
            }
        }
    }
    //Metodo que muestra todos los modulos registrados
    public void listarModulos() {
        //Obtiene la lista de modulos
        ArrayList<Modulo> modulos = mision.getModulos();
        //Verifica que existan modulos
        if (modulos.size() == 0) {
            vista.mostrarMensaje("No hay modulos registrados.");
        }
        else {
            vista.mostrarMensaje("\nMODULOS DE LA MISION");
            //Recorre los modulos en su orden original
            int i = 0;
            while (i < modulos.size()) {
                vista.mostrarModulo(modulos.get(i));
                i = i + 1;
            }
        }
    }
    //Metodo que busca un modulo por ID o nombre
    public void buscarModulo() {
        //Muestra los tipos de busqueda
        vista.mostrarMensaje("\nBUSCAR MODULO");
        vista.mostrarMensaje("1. Buscar por ID");
        vista.mostrarMensaje("2. Buscar por nombre");
        //Solicita el tipo de busqueda
        int opcion = vista.leerOpcion();
        //Variable que guarda el modulo encontrado
        Modulo encontrado = null;
        //Verifica si se desea buscar por ID
        if (opcion == 1) {
            String id = vista.leerTexto("Ingrese el ID: ");
            encontrado = mision.buscarPorId(id);
        }
        //Verifica si se desea buscar por nombre
        else if (opcion == 2) {
            String nombre = vista.leerTexto("Ingrese el nombre: ");
            encontrado = mision.buscarPorNombre(nombre);
        }
        else {
            vista.mostrarMensaje("Tipo de busqueda no valido.");
            return;
        }
        //Muestra el resultado de la busqueda
        if (encontrado != null) {
            vista.mostrarModulo(encontrado);
        }
        else {
            vista.mostrarMensaje("No se encontro el modulo.");
        }
    }
    //Metodo que muestra el catalogo ordenado por costo
    public void mostrarCatalogo() {
        //Obtiene una copia de los modulos ordenados
        ArrayList<Modulo> catalogo = mision.obtenerCatalogoOrdenado();
        //Verifica que existan modulos
        if (catalogo.size() == 0) {
            vista.mostrarMensaje("No hay modulos registrados.");
        }
        else {
            vista.mostrarMensaje("\nCATALOGO ORDENADO POR COSTO");
            //Recorre los modulos ordenados
            int i = 0;
            while (i < catalogo.size()) {
                vista.mostrarModulo(catalogo.get(i));
                i = i + 1;
            }
        }
    }
    //Metodo que avanza un ciclo de la mision
    public void avanzarCiclo() {
        //Solicita a la mision ejecutar un ciclo
        ArrayList<String> resultados = mision.avanzarCiclo();
        //Muestra los resultados de todos los modulos
        int i = 0;
        while (i < resultados.size()) {
            vista.mostrarMensaje(resultados.get(i));
            i = i + 1;
        }
        //Muestra el estado final de los recursos
        vista.mostrarRecursos(mision);
    }
    //Metodo que muestra el resumen de comunicaciones
    public void mostrarComunicaciones() {
        //Solicita a la mision generar el resumen
        ResumenComunicaciones resumen = mision.generarResumenComunicaciones();
        //Muestra el resumen mediante la vista
        vista.mostrarResumen(resumen);
    }
}
