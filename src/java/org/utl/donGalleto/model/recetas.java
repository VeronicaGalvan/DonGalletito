package org.utl.donGalleto.model;

/**
 *
 * @author Sonrie
 */
public class recetas {
    private int idReceta;
    private String nombreReceta;
    private String receta;

    public recetas() {
    }

    public recetas(int idReceta, String nombreReceta, String receta) {
        this.idReceta = idReceta;
        this.nombreReceta = nombreReceta;
        this.receta = receta;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    @Override
    public String toString() {
        return "recetas{" + "idReceta=" + idReceta
                + ", nombreReceta=" + nombreReceta
                + ", receta=" + receta + '}';
    }

   
}
