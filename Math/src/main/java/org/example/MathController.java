package org.example;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController{
    @GetMapping("/tribonacci")
    public Math tribonacci(@RequestParam(value = "n") int n) {
        ArrayList<Integer> resultado= new ArrayList<>();
        for (int i =0;i<=n;i++){
            int numero = calculotribonacci(i);
            resultado.add(numero);
        }
        return new Math(resultado);
    }

    private int calculotribonacci(int n){
        if (n==0){
            return n;
        }
        if (n==1){
            return 0;
        }
        if (n==2){
            return 1;
        }


        return calculotribonacci(n-1)+calculotribonacci(n-2)+calculotribonacci(n-3);
    }


}
