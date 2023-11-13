package com.example.demo.alumno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/alumnos")
public class AlumnoController {
    private final AlumnoService alumnoService;
    @Autowired
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public List<Alumno> getAlumnos(){
        return alumnoService.getAlumnos();
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<String> getAlumnoById(@PathVariable("id") int id){
        Alumno alumno = alumnoService.getAlumnoById(id);
        if(alumno != null) {
            try {

                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(alumno.toString());
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("No existe el alumno con id " + id);
    }
    /*
    * */

    @PostMapping
    public ResponseEntity<String> createAlumno(@RequestBody Alumno alumno){
        try{
            alumnoService.addAlumno(alumno);
            return ResponseEntity.created(URI.create("/alumnos/" + alumno.getId())) // Indica la ubicación del nuevo recurso
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Se ha creado un nuevo alumno" + "\"" + alumno.toString());
        }catch (Exception e){

            //e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(null);
        }

    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteAlumno(@PathVariable("id") int id){
        try {
            alumnoService.deleteAlumno(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Se ha eliminado el alumno con id " + id);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("No existe el alumno con id " + id);
        }
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateAlumno(@PathVariable("id") int id,
                             @RequestParam(required = false) String nombres,
                             @RequestParam(required = false) String apellidos,
                             @RequestParam(required = false) String matricula,
                             @RequestParam(required = false) Double promedio){
        try{
            //System.out.println("Antes de crear el alumno id " + id + " nombres: " + nombres + " apellidos: " + apellidos + " matricula " + matricula + " promedio: " + promedio);
            Alumno alumno = new Alumno(id, nombres, apellidos, matricula, promedio);
            alumnoService.updateAlumno(id, nombres, apellidos, matricula, promedio);
            //System.out.println("Resultado del POST " + id + " nombres: " + nombres + " apellidos: " + apellidos + " matricula " + matricula + " promedio: " + promedio);

            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Se ha modificado el alumno con id " + id + "\"" + alumno.toString());
        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(null);
        }
    }
}
