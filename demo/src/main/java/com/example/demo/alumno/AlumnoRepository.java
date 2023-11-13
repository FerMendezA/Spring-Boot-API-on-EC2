package com.example.demo.alumno;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AlumnoRepository {
    private final List<Alumno> alumnos = new ArrayList<Alumno>();
    private int nextId = 1;

    public AlumnoRepository() {

    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public Alumno getAlumnoById(int id) {
        for (Alumno alumno : alumnos) {
            if (alumno.getId() == id) {
                return alumno;
            }
        }
        return null;
    }

    public Alumno createAlumno(Alumno alumno) {
        alumno.setId(nextId);
        nextId++;
        alumnos.add(alumno);
        return alumno;
    }

    public Alumno updateAlumno(int id, String nombres, String apellidos, String matricula, Double promedio) {
        System.out.println("REcibido en updateAlumno id " + id + " nombres: " + nombres + " apellidos: " + apellidos + " matricula " + matricula + " promedio: " + promedio);

        for (int i = 0; i < alumnos.size(); i++) {
            if (alumnos.get(i).getId() == id) {
                if(nombres != null){
                    alumnos.get(i).setNombres(nombres);
                }
                if(apellidos != null){
                    alumnos.get(i).setApellidos(apellidos);
                }
                if(matricula != null){
                    alumnos.get(i).setMatricula(matricula);
                }
                if(promedio != null){
                    alumnos.get(i).setPromedio(promedio);
                }
                return alumnos.get(i);
            }
        }
        return null;
    }

    public void deleteAlumno(int id) {
        alumnos.removeIf(alumno -> alumno.getId() == id);
    }

    //

}
