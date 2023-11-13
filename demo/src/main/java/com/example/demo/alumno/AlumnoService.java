package com.example.demo.alumno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

import java.util.List;
@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private List<Alumno> alumnos = new ArrayList<>();
    private int nextId = 1;
    //nuevo
    @Autowired
    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public List<Alumno> getAlumnos(){
        return alumnoRepository.getAlumnos();
    }

    public Alumno getAlumnoById(int id){
        return alumnoRepository.getAlumnoById(id);
    }

    public void addAlumno(Alumno alumno) throws ResponseStatusException{
        if(alumno.validarAtributos()){
            alumnoRepository.getAlumnos().add(alumno);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los campos enviados no son válidos");
        }
        System.out.println(alumno);
    }

    public void updateAlumno(int id, String nombres, String apellidos, String matricula, Double promedio) throws ResponseStatusException{
        Alumno alumno = new Alumno(id, nombres, apellidos, matricula, promedio);
        //System.out.println("Validar atributos put " + alu);
        if(alumno.validarAtributosPUT()){

            alumno = alumnoRepository.updateAlumno(id, nombres, apellidos, matricula, promedio);
        }else{
            //System.out.println("Enviaron " + id + " nombres: " + nombres + " apellidos: " + apellidos + " matricula " + matricula + " promedio: " + promedio);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los campos enviados no son válidos" + alumno.toString());
        }
        //System.out.println("PUT" + alumno);
    }

    public void deleteAlumno(int id) throws ResponseStatusException{
        boolean exists = false;
        exists = alumnoRepository.getAlumnos().removeIf(alumno -> alumno.getId() == id);
        if(!exists){
            throw new IllegalStateException("El alumno con id " + id + "no existe");
        }
    }
}
