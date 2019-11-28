/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PMPALMEIRA15
 */
public class Banco {

    public static Connection con = null;
    public Banco conexao;

    public static void conectar() throws SQLException {
        //System.out.println("Conectando ao banco...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/prosangue", "root", ""); //Usuário: root - senha: civitas@123

            //System.out.println("Conectado.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe não encontrada, adicione o driver nas bibliotecas.");
            Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desconectar() throws SQLException {
        con.close();
    }
//    public static void conectarMySql() {
//        //System.out.println("Conectando ao banco...");
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "root", null);
//            //System.out.println("Conectado.");
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Classe não encontrada, adicione o driver nas bibliotecas.");
//            Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException e) {
//            System.out.println(e);
//
//        }
//    }
}
