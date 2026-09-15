package controller;

import java.util.Scanner;
import model.*;
import util.InputValidator;
import services.Registros;


public class RegistroController {
    Scanner scr = new Scanner(System.in);
    InputValidator inputValidator = new InputValidator();


    public void flujoRegistro(){

        Boolean continuar = true;
        while (continuar) {

            int elegirRegistro = inputValidator.elegirRegistro(scr);
            switch (elegirRegistro) {
                case 1:
                    try {
                        System.out.println("Ingresa el codigo del producto");
                        String codigo = scr.nextLine();

                    } catch (IllegalArgumentException e) {
                        System.err.println("Error " + e.getMessage());
                    }
                    break;
                case 2:
                    ProveedorController proveedorController = new ProveedorController();
                    proveedorController.registrarProveedor();
                    break;
            }
        }

    }


}