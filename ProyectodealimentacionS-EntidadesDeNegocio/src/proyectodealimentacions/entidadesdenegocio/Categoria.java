
package proyectodealimentacions.entidadesdenegocio;

import java.util.ArrayList;

public class Categoria {
    private int id;
    private String desayuno;
    private String porProteina;
    private String porTiempo;
    private String bebidas;
    private int top_aux;
    private ArrayList<Recetas> recetas;

    public Categoria() {
    }

    public Categoria(int id, String desayuno, String porProteina, String porTiempo, String bebidas) {
        this.id = id;
        this.desayuno = desayuno;
        this.porProteina = porProteina;
        this.porTiempo = porTiempo;
        this.bebidas = bebidas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesayuno() {
        return desayuno;
    }

    public void setDesayuno(String desayuno) {
        this.desayuno = desayuno;
    }

    public String getPorProteina() {
        return porProteina;
    }

    public void setPorProteina(String porProteina) {
        this.porProteina = porProteina;
    }

    public String getPorTiempo() {
        return porTiempo;
    }

    public void setPorTiempo(String porTiempo) {
        this.porTiempo = porTiempo;
    }

    public String getBebidas() {
        return bebidas;
    }

    public void setBebidas(String bebidas) {
        this.bebidas = bebidas;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public ArrayList<Recetas> getRecetas() {
        return recetas;
    }

    public void setRecetas(ArrayList<Recetas> recetas) {
        this.recetas = recetas;
    }
    
}

