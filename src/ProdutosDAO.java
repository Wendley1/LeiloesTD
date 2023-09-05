/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public Boolean cadastrarProduto (ProdutosDTO produto){ 
        String slq = "insert into produtos(nome, valor, status) values (?,?,?);";
        
        boolean cadastrado = false;
        
        try (Connection conn = new conectaDAO().connectDB();
            PreparedStatement statement = conn.prepareStatement(slq))
        {
            statement.setString(1, produto.getNome());
            statement.setInt(2, produto.getValor());
            statement.setString(3, produto.getStatus());
            
            if (statement.executeUpdate() > 0) 
            {
                cadastrado = true;
            }
            
            conn.close();
            
        } catch (SQLException ex)
        {
            System.out.println(ex);
        }
        
        return cadastrado;
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

