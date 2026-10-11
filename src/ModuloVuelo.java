//Estuardo Antonio Molina 26878
//Clase que representa un modulo de vuelo del satelite Quetzal-2
public class ModuloVuelo extends Modulo {
    //Atributos propios del modulo de vuelo
    private String tipoInstrumento;
    private double datosPorCiclo;
    private double consumoEnergia;
    private int ciclosAcumulados;
    private double totalRecolectado;
    //Constructor que inicializa los datos del modulo de vuelo
    public ModuloVuelo(String id, String nombre, int salud, boolean activo, double costoConstruccion, String tipoInstrumento, double datosPorCiclo, double consumoEnergia) {
        //Inicializa los atributos heredados de Modulo
        super(id, nombre, salud, activo, costoConstruccion);
        //Valida el tipo de instrumento
        if (!tipoInstrumento.equalsIgnoreCase("Camara") && !tipoInstrumento.equalsIgnoreCase("Sensor")) {
            throw new IllegalArgumentException("El instrumento debe ser Camara o Sensor.");
        }
        //Valida la cantidad de datos por ciclo
        if (datosPorCiclo <= 0) {
            throw new IllegalArgumentException("Los datos por ciclo deben ser mayores que 0.");
        }
        //Valida el consumo de energia
        if (consumoEnergia < 0) {
            throw new IllegalArgumentException("El consumo de energia no puede ser negativo.");
        }
        //Asigna los valores propios del modulo
        this.tipoInstrumento = tipoInstrumento;
        this.datosPorCiclo = datosPorCiclo;
        this.consumoEnergia = consumoEnergia;
        this.ciclosAcumulados = 0;
        this.totalRecolectado = 0;
    }
    //Devuelve el tipo de instrumento
    public String getTipoInstrumento() {
        return tipoInstrumento;
    }
    //Devuelve la cantidad de datos que recolecta por ciclo
    public double getDatosPorCiclo() {
        return datosPorCiclo;
    }
    //Devuelve el consumo de energia
    public double getConsumoEnergia() {
        return consumoEnergia;
    }
    //Devuelve los ciclos en los que logro recolectar datos
    public int getCiclosAcumulados() {
        return ciclosAcumulados;
    }
    //Devuelve el total historico recolectado
    public double getTotalRecolectado() {
        return totalRecolectado;
    }
    //Metodo que realiza la recoleccion de datos durante un ciclo
    @Override
    public String procesarCiclo(Mision mision) {
        //Verifica si el modulo esta inactivo
        if (getActivo() == false || getSalud() == 0) {
            return "El modulo de vuelo " + getNombre() + " no recolecto datos porque esta inactivo.";
        }
        //Verifica si existe energia suficiente
        if (mision.getEnergiaDisponible() < consumoEnergia) {
            return "El modulo de vuelo " + getNombre() + " no recolecto datos por energia insuficiente.";
        }
        //Consume la energia necesaria para operar
        mision.consumirEnergia(consumoEnergia);
        //Agrega los datos recolectados a los pendientes en orbita
        mision.agregarDatos(datosPorCiclo);
        //Actualiza los acumulados del modulo
        ciclosAcumulados = ciclosAcumulados + 1;
        totalRecolectado = totalRecolectado + datosPorCiclo;
        //Devuelve el resultado de la operacion
        return "El modulo de vuelo " + getNombre() + " recolecto " + datosPorCiclo + " MB y consumio " + consumoEnergia + " unidades de energia.";
    }
    //Metodo que muestra toda la informacion del modulo
    @Override
    public String toString() {
        return super.toString() + "\nTipo de instrumento: " + tipoInstrumento + "\nDatos por ciclo: " + datosPorCiclo + " MB" + "\nConsumo de energia: " + consumoEnergia + "\nCiclos acumulados: " + ciclosAcumulados + "\nTotal recolectado: " + totalRecolectado + " MB";
    }
}
