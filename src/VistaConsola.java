import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
//Estuardo Antonio Molina 26878
//Clase que representa la vista del programa y maneja la consola
public class VistaConsola {
    //Scanner que permite leer los datos del usuario
    private Scanner teclado;
    //Constructor que inicializa el Scanner
    public VistaConsola() {
        teclado = new Scanner(System.in);
    }
    //Metodo que muestra el menu principal
    public void mostrarMenu() {
        System.out.println("\nDEFENSA DE QTZ2");
        System.out.println("1. Listar todos los modulos");
        System.out.println("2. Buscar modulo por ID o nombre");
        System.out.println("3. Consultar catalogo ordenado por costo");
        System.out.println("4. Avanzar un ciclo");
        System.out.println("5. Resumen de comunicaciones");
        System.out.println("6. Salir");
    }
    //Metodo que solicita una opcion numerica al usuario
    public int leerOpcion() {
        //Solicita la opcion del menu
        System.out.print("Seleccione una opcion: ");
        try {
            //Lee la opcion ingresada
            int opcion = teclado.nextInt();
            //Limpia el salto de linea
            teclado.nextLine();
            return opcion;
        }
        //Controla cuando el usuario escribe texto en lugar de un numero
        catch (InputMismatchException e) {
            //Limpia la entrada incorrecta
            teclado.nextLine();
            //Muestra un mensaje y devuelve una opcion invalida
            System.out.println("Debe ingresar un numero.");
            return -1;
        }
    }
    //Metodo que solicita un texto al usuario
    public String leerTexto(String mensaje) {
        //Muestra el mensaje recibido
        System.out.print(mensaje);
        //Devuelve el texto ingresado
        return teclado.nextLine();
    }
    //Metodo que muestra un mensaje en consola
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    //Metodo que muestra la informacion de un modulo
    public void mostrarModulo(Modulo modulo) {
        //Verifica que el modulo exista
        if (modulo != null) {
            //Utiliza toString mediante polimorfismo
            System.out.println("\n" + modulo.toString());
        }
        else {
            System.out.println("No se encontro el modulo.");
        }
    }
    //Metodo que muestra los recursos actuales de la mision
    public void mostrarRecursos(Mision mision) {
        System.out.println("\nRECURSOS DE LA MISION");
        System.out.println("Ciclo actual: " + mision.getNumeroCiclo());
        System.out.println("Energia disponible: " + mision.getEnergiaDisponible());
        System.out.println("Datos pendientes en orbita: " + mision.getDatosOrbita() + " MB");
        System.out.println("Datos historicos descargados: " + mision.getDatosDescargados() + " MB");
    }
    //Metodo que muestra el resumen de comunicaciones
    public void mostrarResumen(ResumenComunicaciones resumen) {
        System.out.println("\nRESUMEN DE COMUNICACIONES");
        System.out.println("Total de modulos de tierra: " + resumen.getTotalModulosTierra());
        System.out.println("Modulos de tierra activos: " + resumen.getModulosActivos());
        System.out.println("Capacidad total de descarga activa: " + resumen.getCapacidadTotal() + " MB por ciclo");
        System.out.println("Total historico descargado: " + resumen.getTotalDescargado() + " MB");
        //Obtiene los modulos con mayor descarga
        ArrayList<ModuloTierra> mayores = resumen.getModulosMayorDescarga();
        //Verifica que existan modulos de tierra
        if (mayores.size() == 0) {
            System.out.println("No hay modulos de tierra registrados.");
        }
        else {
            System.out.println("\nMODULOS CON MAYOR DESCARGA HISTORICA");
            //Recorre todos los modulos que comparten la mayor descarga
            int i = 0;
            while (i < mayores.size()) {
                ModuloTierra modulo = mayores.get(i);
                System.out.println("Nombre: " + modulo.getNombre() + " | ID: " + modulo.getId() + " | Estacion: " + modulo.getEstacion() + " | Total descargado: " + modulo.getTotalDescargado() + " MB");
                i = i + 1;
            }
        }
    }
}
