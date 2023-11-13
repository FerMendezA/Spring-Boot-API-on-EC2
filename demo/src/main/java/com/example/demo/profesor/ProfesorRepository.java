package com.example.demo.profesor;

import com.example.demo.alumno.Alumno;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfesorRepository {
    private final List<Profesor> profesores = new ArrayList<Profesor>();
    private int nextId = 1;

    public ProfesorRepository() {

    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public Profesor getProfesorById(int id) {
        for (Profesor profesor : profesores) {
            if (profesor.getId() == id) {
                return profesor;
            }
        }
        return null;
    }

    public Profesor createProfesor(Profesor profesor) {
        profesor.setId(nextId);
        nextId++;
        profesores.add(profesor);
        return profesor;
    }

    //public Profesor updateProfesor(int id, Profesor profesor) {
    //    for (int i = 0; i < profesores.size(); i++) {
    //        if (profesores.get(i).getId() == id) {
   //             profesor.setId(id);
    //            profesores.set(i, profesor);
     //           return profesor;
      //      }
       // }
       // return null;
    //}

    public Profesor updateProfesor(int id, Integer numeroEmpleado, String nombres, String apellidos, Integer horasClase) {
        //System.out.println("REcibido en updateAlumno id " + id + " nombres: " + nombres + " apellidos: " + apellidos + " matricula " + matricula + " promedio: " + promedio);

        for (int i = 0; i < profesores.size(); i++) {
            if (profesores.get(i).getId() == id) {
                if(numeroEmpleado != null){
                    profesores.get(i).setNumeroEmpleado(numeroEmpleado);
                }
                if(nombres != null){
                    profesores.get(i).setNombres(nombres);
                }
                if(apellidos != null){
                    profesores.get(i).setApellidos(apellidos);
                }

                if(horasClase != null){
                    profesores.get(i).setHorasClase(horasClase);
                }
                return profesores.get(i);
            }
        }
        return null;
    }

    public void deleteProfesor(int id) {
        profesores.removeIf(profesor -> profesor.getId() == id);
    }
}
