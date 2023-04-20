/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.Programa08CC;

import org.uv.Programa08CC.DTOEmpleado;
import org.uv.Programa08CC.EmpleadosRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zS20006736
 */
@RestController
@RequestMapping("/api")
public class EmpleadosController {

    @Autowired
    EmpleadosRepository empleadosRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hola mundo";
    }

    @GetMapping("/empleados")
    public ResponseEntity<List<DTOEmpleado>> getAllEmployess() {
        try {
            List<DTOEmpleado> ltsEmpleado = new ArrayList<>();
            empleadosRepository.findAll().forEach(ltsEmpleado::add);
            return new ResponseEntity<>(ltsEmpleado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<DTOEmpleado> getEmployeeById(@PathVariable("id") long id) {
        Optional<DTOEmpleado> empData = empleadosRepository.findById(id);
        if (empData.isPresent()) {
            return new ResponseEntity<>(empData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/empleados")
    public ResponseEntity<DTOEmpleado> createEmployee(@RequestBody DTOEmpleado emp) {
        try {
            DTOEmpleado _emp = empleadosRepository.save(new DTOEmpleado(emp.getNombre(), emp.getDireccion(), emp.getTelefono()));
            return new ResponseEntity<>(_emp, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {
        try {
            empleadosRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/empleados/{id}")
    public ResponseEntity<DTOEmpleado> updateEmployee(@PathVariable("id") long id, @RequestBody DTOEmpleado emp) {
        Optional<DTOEmpleado> empData = empleadosRepository.findById(id);
        if(empData.isPresent()){
            DTOEmpleado _emp = empData.get();
            _emp.setNombre(emp.getNombre());
            _emp.setDireccion(emp.getDireccion());
            _emp.setTelefono(emp.getTelefono());
            return new ResponseEntity<>(empleadosRepository.save(_emp), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
}
