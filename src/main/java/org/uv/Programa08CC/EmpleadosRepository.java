/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.Programa08CC;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.Programa08CC.DTOEmpleado;

/**
 *
 * @author zS20006736
 */
public interface EmpleadosRepository extends JpaRepository<DTOEmpleado, Long>{
    
}
