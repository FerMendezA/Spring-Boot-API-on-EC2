package com.example.demo.alumno;

public class Alumno {
    private int id;
    private String nombres;
    private String apellidos;
    private String matricula;
    private Double promedio;

    public Alumno(){

    }
    public Alumno(int id, String nombres, String apellidos, String matricula, Double promedio) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.matricula = matricula;
        this.promedio = promedio;
    }

    public Alumno(String nombres, String apellidos, String matricula, Double promedio) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.matricula = matricula;
        this.promedio = promedio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        //return "{" +
        //        "id=" + id +
        //        ", nombres='" + nombres + '\'' +
        //        ", apellidos='" + apellidos + '\'' +
        //        ", matricula='" + matricula + '\'' +
        //        ", promedio=" + promedio +
        //        '}';
        return "{"
                + "\"id\":\"" + this.id + "\","
                + "\"nombres\":\"" + this.nombres + "\","
                + "\"apellidos\":\"" + this.apellidos + "\","
                + "\"matricula\":\"" + this.matricula + "\","
                + "\"promedio\":" + this.promedio
                + "}";
    }

    public boolean validarAtributos() {
        if(id <= 0){
            return false;
        }
        if (nombres == null ) {
            return false;
        }

        if (apellidos == null || apellidos.trim().isEmpty()) {
            return false;
        }

        if (matricula == null || matricula.trim().isEmpty()){
            return false;
        }

        if (promedio == null || promedio < 0 ){
            return false;
        }

        return true;
    }

    public boolean validarAtributosPUT(){
        if(id <= 0){
            return false;
        }else if(nombres == null && apellidos == null && matricula == null && promedio == null){
            return false;
        }
        if(promedio!=null){
            if ( promedio < 0 ){
                return false;
            }
        }

        if(matricula != null){
            for (int i = 0; i < matricula.length(); i++) {
                if ((!Character.isLetterOrDigit(matricula.charAt(i)))) {
                    return false;
                }
            }
        }
        return true;
    }
}
