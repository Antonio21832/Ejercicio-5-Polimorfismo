//Estuardo Antonio Molina 26878
//Clase que representa un modulo de energia de la mision Quetzal-2
public class ModuloEnergia extends Modulo {
    //Atributos propios del modulo de energia
    private double energiaPorCiclo;
    private int ciclosAcumulados;
    private double totalGenerado;
    //Constructor que inicializa los datos del modulo de energia
    public ModuloEnergia(String id, String nombre, int salud, boolean activo, double costoConstruccion, double energiaPorCiclo) {
        //Inicializa los atributos heredados de Modulo
        super(id, nombre, salud, activo, costoConstruccion);
        //Valida que la energia generada sea mayor que cero
        if (energiaPorCiclo <= 0) {
            throw new IllegalArgumentException("La energia por ciclo debe ser mayor que 0.");
        }
        //Asigna los valores propios del modulo
        this.energiaPorCiclo = energiaPorCiclo;
        this.ciclosAcumulados = 0;
        this.totalGenerado = 0;
    }
    //Devuelve la energia generada por ciclo
    public double getEnergiaPorCiclo() {
        return energiaPorCiclo;
    }
    //Devuelve los ciclos en los que genero energia
    public int getCiclosAcumulados() {
        return ciclosAcumulados;
    }
    //Devuelve el total historico de energia generada
    public double getTotalGenerado() {
        return totalGenerado;
    }
    //Metodo que genera energia durante un ciclo
    @Override
    public String procesarCiclo(Mision mision) {
        //Verifica si el modulo esta inactivo o no tiene salud
        if (getActivo() == false || getSalud() == 0) {
            return "El modulo de energia " + getNombre() + " no genero energia porque esta inactivo.";
        }
        //Agrega la energia generada a los recursos de la mision
        mision.agregarEnergia(energiaPorCiclo);
        //Actualiza los acumulados del modulo
        ciclosAcumulados = ciclosAcumulados + 1;
        totalGenerado = totalGenerado + energiaPorCiclo;
        //Devuelve el resultado de la operacion
        return "El modulo de energia " + getNombre() + " genero " + energiaPorCiclo + " unidades de energia.";
    }
    //Metodo que muestra toda la informacion del modulo
    @Override
    public String toString() {
        return super.toString() + "\nEnergia por ciclo: " + energiaPorCiclo + "\nCiclos acumulados: " + ciclosAcumulados + "\nTotal generado: " + totalGenerado;
    }
}
