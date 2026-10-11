//Estuardo Antonio Molina 26878
//Clase principal que inicia el simulador de la mision Quetzal-2
public class Main {
    //Metodo principal donde comienza la ejecucion del programa
    public static void main(String[] args) {
        //Crea la mision con 10 unidades de energia inicial
        Mision mision = new Mision(10);
        //Crea los modulos de energia
        ModuloEnergia energia1 = new ModuloEnergia("E001", "Panel Solar Principal", 100, true, 5000, 20);
        ModuloEnergia energia2 = new ModuloEnergia("E002", "Panel Solar Secundario", 90, true, 3500, 15);
        ModuloEnergia energia3 = new ModuloEnergia("E003", "Panel Solar Reserva", 80, false, 2500, 10);
        //Crea los modulos de vuelo
        ModuloVuelo vuelo1 = new ModuloVuelo("V001", "Camara Principal", 100, true, 8000, "Camara", 15, 8);
        ModuloVuelo vuelo2 = new ModuloVuelo("V002", "Sensor Termico", 95, true, 6500, "Sensor", 12, 6);
        ModuloVuelo vuelo3 = new ModuloVuelo("V003", "Camara Secundaria", 85, true, 5500, "Camara", 10, 5);
        ModuloVuelo vuelo4 = new ModuloVuelo("V004", "Sensor de Reserva", 70, false, 4000, "Sensor", 8, 4);
        //Crea los modulos de tierra
        ModuloTierra tierra1 = new ModuloTierra("T001", "Antena Principal", 100, true, 7000, "Estacion Central", 10, 5);
        ModuloTierra tierra2 = new ModuloTierra("T002", "Antena Secundaria", 90, true, 6000, "Estacion Norte", 12, 6);
        ModuloTierra tierra3 = new ModuloTierra("T003", "Antena de Reserva", 75, false, 4500, "Estacion Sur", 8, 4);
        //Registra los modulos en el orden en que participaran
        mision.agregarModulo(energia1);
        mision.agregarModulo(vuelo1);
        mision.agregarModulo(tierra1);
        mision.agregarModulo(energia2);
        mision.agregarModulo(vuelo2);
        mision.agregarModulo(tierra2);
        mision.agregarModulo(vuelo3);
        mision.agregarModulo(energia3);
        mision.agregarModulo(vuelo4);
        mision.agregarModulo(tierra3);
        //Crea la vista del programa
        VistaConsola vista = new VistaConsola();
        //Crea el controlador que conecta la vista con la mision
        ControladorMision controlador = new ControladorMision(mision, vista);
        //Inicia el menu principal
        controlador.iniciar();
    }
}
