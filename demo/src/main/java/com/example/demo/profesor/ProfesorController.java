package com.example.demo.profesor;

import com.example.demo.alumno.Alumno;
import com.example.demo.alumno.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/profesores")
public class ProfesorController {
    private final ProfesorService profesorService;
    @Autowired
    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public List<Profesor> getProfesores(){
        return profesorService.getProfesores();
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<String> getProfesorById(@PathVariable("id") int id){
        Profesor profesor = profesorService.getProfesorById(id);
        if(profesor != null){
            try {
                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(profesor.toString());
            } catch (Exception e) {
                //e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("No existe el profesor con id " + id);
    }

    @PostMapping
    public ResponseEntity<String> createProfesor(@RequestBody Profesor profesor){
        try{
            profesorService.addProfesor(profesor);
            return ResponseEntity.created(URI.create("/profesores/" + profesor.getId())) // Indica la ubicación del nuevo recurso
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Se ha creado un nuevo profesor\"" + profesor.toString());
        }catch (Exception e){
            //e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(null);
        }
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteProfesor(@PathVariable("id") int id){
        try {
            profesorService.deleteProfesor(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Se ha eliminado el profesor con id " + id);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("No existe el profesor con id " + id);
        }
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateProfesor(@PathVariable("id") int id,
                               @RequestParam(required = false) Integer numeroEmpleado,
                             @RequestParam(required = false) String nombres,
                             @RequestParam(required = false) String apellidos,
                             @RequestParam(required = false) Integer horasClase){

        try{
            Profesor profesor = new Profesor(id, numeroEmpleado, nombres, apellidos, horasClase);
            profesorService.updateProfesor(id, numeroEmpleado, nombres, apellidos, horasClase);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Se ha modificado el profesor con id " + id + "\"" + profesor.toString());
        }catch (Exception e){


            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(null);
        }

    }
}

/*
*
*
* */