package controller;

import java.util.Scanner;
import model.*;
import util.InputValidator;
import services.Registros;


public class RegistroController {
    Scanner scr = new Scanner(System.in);
    InputValidator inputValidator = new InputValidator();
    Registros registros = new Registros();


    public void flujoRegistro(){

        Boolean continuar = true;
        while (continuar) {

            int elegirRegistro = inputValidator.elegirRegistro(scr);
            switch (elegirRegistro) {
                case 1:
                    try {
                        System.out.println("Ingresa el codigo del producto");
                        Long codigo = Long.parseLong(scr.nextLine());
                        codigo = registros.registrarProducto(codigo);
                        if (codigo != null) {
                            System.out.println("Ingresa el nombre del producto");
                            String nombreProd = scr.nextLine();
                            System.out.println("Ingresa la categoria en la que se encuentra el producto");
                            String categoria = scr.nextLine();
                            double precio = inputValidator.validarNegativos(scr, "Ingresa el precio del producto", true);
                            System.out.println("¡PRODUCTO REGISTRADO CON EXITO!");
                        } else {
                            System.err.println("El código del producto ya existe.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error " + e.getMessage());
                    }
                    break;
                case 2:
                    break;
            }
        }

    }


}