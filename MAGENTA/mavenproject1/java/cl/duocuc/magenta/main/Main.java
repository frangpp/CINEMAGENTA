/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duocuc.magenta.main;

import cl.duocuc.magenta.database.DatabaseConnection;
import cl.duocuc.magenta.gui.MainFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author franciscagoeppinger
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            System.out.println("----Iniciando Cine Magenta----");
            
            if (DatabaseConnection.probarConexion()) {
                System.out.println("Conexión exitosa! Abriendo interfaz...");
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            } else {
                System.out.println("Error: No se pudo conectar a la BD");
            }
        });
    }
}
