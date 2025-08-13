package com.senai.calculadora.controllers;

import com.senai.calculadora.DTOs.CalculadoraDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calc")
public class CalculadoraController {



    @GetMapping("/soma")
    public ResponseEntity<CalculadoraDTO> soma(@RequestBody CalculadoraDTO calculadoraDTO) {
        calculadoraDTO.setResultado(calculadoraDTO.getNum1() + calculadoraDTO.getNum2());
        return ResponseEntity.ok(calculadoraDTO);
    }
    @GetMapping("/subtracao")
    public ResponseEntity<CalculadoraDTO> subtracao(@RequestBody CalculadoraDTO calculadoraDTO){
        calculadoraDTO.setResultado(calculadoraDTO.getNum1() - calculadoraDTO.getNum2());
        return ResponseEntity.ok(calculadoraDTO);
    }
    @GetMapping("/multiplicacao")
    public ResponseEntity<CalculadoraDTO> multiplicacao(@RequestBody CalculadoraDTO calculadoraDTO){
        calculadoraDTO.setResultado(calculadoraDTO.getNum1() * calculadoraDTO.getNum2());
        return ResponseEntity.ok(calculadoraDTO);
    }
    @GetMapping("/divisao")
    public ResponseEntity<CalculadoraDTO> divisao(@RequestBody CalculadoraDTO calculadoraDTO){
        calculadoraDTO.setResultado(calculadoraDTO.getNum1() / calculadoraDTO.getNum2());
        return ResponseEntity.ok(calculadoraDTO);
    }

}
