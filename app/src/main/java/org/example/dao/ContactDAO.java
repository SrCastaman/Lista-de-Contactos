package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.example.db.Database;
import org.example.model.Contact;

public class ContactDAO {
    public void insert(Contact c){
        String sql = "INSERT INTO contacts(name, phone, email) VALUES(?,?,?)";
        try (Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, c.getName());
            pstmt.setString(2, c.getPhone());
            pstmt.setString(3, c.getEmail());
            pstmt.executeUpdate();


            try(ResultSet rs = pstmt.getGeneratedKeys()){
                if(rs.next()) {c.setId(rs.getInt(1));}
            }

            }catch(SQLException e){
                e.printStackTrace();
            }
    }


    public void update(int id, String name, String phone, String email) {
        StringBuilder sql = new StringBuilder("UPDATE contacts SET ");
        List<Object> params = new ArrayList<>();
        if (name != null) { sql.append("name=?, "); params.add(name);}
        if (phone != null) { sql.append("phone=?, "); params.add(phone);}
        if (email != null) { sql.append("email=?, "); params.add(email);}


        sql.setLength(sql.length() - 2);
        sql.append("WHERE id=?");
        params.add(id);

        try (Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            for (int i = 0; i < params.size(); i++){
                pstmt.setObject(i+1, params.get(i));
            }
            pstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }



    public void delete(int id){
        String sql = "DELETE FROM contacts WHERE id=?";
        try(Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1,id);
            pstmt.executeUpdate();    

        } catch (SQLException e){
            e.printStackTrace();
        }
    }



    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts";
        try(Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
                
            while (rs.next()) {
                Contact c = new Contact(
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("email")
                );
                c.setId(rs.getInt("id"));
                contacts.add(c);
            }


            } catch(SQLException e){
                e.printStackTrace();
            }
        
        
        return contacts;
    }

    public Contact getContact(int id){
        String sql = "SELECT * FROM contacts WHERE id = ?";
        Contact c = null;
        try(Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();    
            if (rs.next()){
                c = new Contact();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
            }     

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

}
