package com.telusko.demorest;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class AlienRepository {
	
	Connection con = null;
	
	public AlienRepository(){
		String url="jdbc:mysql://localhost:3306/restdb";
		String username = "root";
		String password = "root";
		try{
			Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection(url,username,password);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public List<Alien> getAliens(){
		List<Alien> aliens=new ArrayList<>();
		String query = "Select * from alien";
		try{
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next()){
			Alien a=new Alien();
			a.setId(rs.getInt(1));
			a.setName(rs.getString(2));
			a.setPoints(rs.getInt(3));
			
			aliens.add(a);
		}
		
		}catch(Exception e){
			System.out.println(e);
		}
		return aliens;
	}
	public Alien getAlien(int id){
		String query = "Select * from alien where id="+id;
		Alien a=new Alien();
		try{
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(query);
		if(rs.next()){
			
			a.setId(rs.getInt(1));
			a.setName(rs.getString(2));
			a.setPoints(rs.getInt(3));
			
		}
		
		}catch(Exception e){
			System.out.println(e);
		}
		return a;
	}

	public void create(Alien a1) {
		String query = "insert into alien values(?,?,?)";
		try{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, a1.getId());
			ps.setString(2, a1.getName());
			ps.setInt(3, a1.getPoints());
			
			ps.executeUpdate();
			
			}catch(Exception e){
				System.out.println(e);
			}
	}
	
	public void update(Alien a1) {
		String query = "update alien set name=?, points=? where id=?";
		try{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(3, a1.getId());
			ps.setString(1, a1.getName());
			ps.setInt(2, a1.getPoints());
			
			ps.executeUpdate();
			
			}catch(Exception e){
				System.out.println(e);
			}
	}

	public void delete(int id) {
		String query = "delete from alien where id=?";
		try{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			
			}catch(Exception e){
				System.out.println(e);
			}
		
	}

}
