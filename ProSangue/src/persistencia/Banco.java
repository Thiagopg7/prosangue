/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

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

    public static void executarArquivoSQL() throws SQLException {
        boolean resultado = false;
        Statement declaracao = null;
        Banco.conectar();

        try {
            declaracao = Banco.con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner arquivoSQL = null;
        try {
            arquivoSQL = new Scanner(new File("Esquema.sql"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
        }
        arquivoSQL.useDelimiter(Pattern.compile(";"));
        while (arquivoSQL.hasNext()) {
            try {
                String comando = arquivoSQL.next();
                System.out.println(comando);
                declaracao.execute(comando);
                resultado = true;
            } catch (SQLException ex) {
                Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //return resultado;
    }
}
