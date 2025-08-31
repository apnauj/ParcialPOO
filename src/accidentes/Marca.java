package accidentes;

import java.util.ArrayList;

public class Marca extends Carro {
    private long id;
    private String nombre;
    private String pais;

    // Relación: 1 marca -> n carros
    private ArrayList<Carro> carros;

    public Marca() {
        this.carros = new ArrayList<>();
    }

    public Marca(long id, String nombre, String pais) {
        this();
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
    }

    // Getters/Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public ArrayList<Carro> getCarros() { return carros; }

    // Helpers
    public void agregarCarro(Carro carro) {
        if (carro == null) return;
        if (!this.carros.contains(carro)) {
            this.carros.add(carro);
        }
        if (carro.getMarca() != this) {
            carro.setMarca(this);
        }
    }

    public void removerCarro(Carro carro) {
        if (carro == null) return;
        this.carros.remove(carro);
        if (carro.getMarca() == this) {
            carro.setMarca(null);
        }
    }

    @Override
    public String toString() {
        return "Marca{id=" + id + ", nombre='" + nombre + "', pais='" + pais + "'}";
    }
}
