/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duocuc.magenta.main;

import cl.duocuc.magenta.database.DatabaseConnection;
/**
 *
 * @author franciscagoeppinger
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Cine Magenta...");
        System.out.println("Probando conexión a la base de datos...");
        
        if (DatabaseConnection.probarConexion()) {
            System.out.println("Conexión exitosa! Sistema listo.");
        } else {
            System.out.println("Error: No se pudo conectar a la base de datos");
            System.out.println("Verifica que:");
            System.out.println("   - MySQL esté ejecutándose");
            System.out.println("   - La base de datos 'Cine_DB' exista");
            System.out.println("   - Las credenciales en DatabaseConnection sean correctas");
        }
    }
}
