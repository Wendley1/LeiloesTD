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
        String sql = "select * from produtos";
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        
        try(Connection conn = new conectaDAO().connectDB();
            PreparedStatement statement = conn.prepareStatement(sql); 
            ResultSet rs = statement.executeQuery()){
            
            while(rs.next())
            {               
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString(("status")));
                
                listagem.add(produto);
            }                   
            
            conn.close();
            
        }catch(SQLException ex)
        {
            System.out.println(ex);
            return null;
        }
        
        return listagem;
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        String sql = "select * from produtos where status = 'Vendido'";
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        
        try(Connection conn = new conectaDAO().connectDB();
            PreparedStatement statement = conn.prepareStatement(sql); 
            ResultSet rs = statement.executeQuery()){
            
            while(rs.next())
            {               
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString(("status")));
                
                listagem.add(produto);
            }                   
            
            conn.close();
            
        }catch(SQLException ex)
        {
            System.out.println(ex);
            return null;
        }
        
        return listagem;
    }   
    
    public Boolean venderProduto (int produto_id){ 
        
        String slq = "update produtos set status = 'Vendido' where id = ?";        
        boolean vendido = false;
        
        try (Connection conn = new conectaDAO().connectDB();
            PreparedStatement statement = conn.prepareStatement(slq))
        {
            statement.setInt(1, produto_id);
            
            if (statement.executeUpdate() > 0) 
            {
                vendido = true;
            }
            
            conn.close();
            
        } catch (SQLException ex)
        {
            System.out.println(ex);
        }
        
        return vendido;
    }
}

