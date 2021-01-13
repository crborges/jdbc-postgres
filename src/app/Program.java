package app;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import entities.Order;
import entities.Product;
import entities.orderStatus;

public class Program {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = DB.getConnection();
	
		Statement st = conn.createStatement();
			
		ResultSet rs1 = st.executeQuery("select * from tb_product");
		while (rs1.next()) {
			Product p = instantiateProduct(rs1);
			System.out.println("\n\n\nCaracteristicas do produto \nIdentificador "+p.getId()+"\nNome "+p.getName()+"\nPreço "+p.getPrice()+"\nDescrição "+p.getDescription()+"\nUrl imagem "+p.getImageUri()+"\ntoString "+p.toString()+"\n\n\n");
		}
		
		
		
		ResultSet rs2 = st.executeQuery("SELECT * FROM tb_order"
				+ " INNER JOIN tb_order_product ON tb_order.id = tb_order_product.order_id"
				+ " INNER JOIN tb_product ON tb_product.id = tb_order_product.product_id");
		
		Map<Long, Order> map = new HashMap<>();
		Map<Long, Product> prds = new HashMap<>();
		while (rs2.next()) {
			long ordeId= rs2.getLong("order_id");
			if(map.get(ordeId)== null) {
				Order o = instantiateOrder(rs2);
				map.put(ordeId, o);
			}
			
			
			long productId = rs2.getLong("product_id");
			if(prds.get(productId)== null) {
				Product p = instantiateProduct(rs2);
				prds.put(productId, p);
			}
			
			map.get(ordeId).getProducts().add(prds.get(productId));
			
			
			for (long orderId : map.keySet()) {
				System.out.println(map.get(orderId));
				
				System.out.println("\n\n\nCaracteristicas da ordem \nIdentificador "+map.get(orderId).getId()+"\nLatitude "+map.get(orderId).getLatitude()+"\nLongitude "+map.get(orderId).getLongitude()+"\nData/hora pedido "+map.get(orderId).getMoment()+"\nHora pedido "+map.get(orderId).getStatus()+"\nProdutos\n\n");
				for (Product p : map.get(orderId).getProducts()) {
					System.out.println("\nCaracteristicas do produto \nIdentificador "+p.getId()+"\nNome "+p.getName()+"\nPreço "+p.getPrice()+"\nDescrição "+p.getDescription()+"\nUrl imagem "+p.getImageUri()+"\ntoString "+p.toString()+"\n");				
					
				}
			}
			
			//public List<Product> getProducts();
			//
		}
		
		
	}

	private static Product instantiateProduct(ResultSet rs) throws SQLException{
		Product p = new Product();
		try {
			p.setId(rs.getLong("id"));
			p.setName(rs.getString("name"));
			p.setPrice(rs.getDouble("price"));
			p.setDescription(rs.getString("description"));
			p.setImageUri(rs.getString("image_uri"));
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	
	private static Order instantiateOrder(ResultSet rs) throws SQLException{
		Order o = new Order();
		try {
			o.setId(rs.getLong("id"));
			o.setLatitude(rs.getDouble("latitude"));
			o.setLongitude(rs.getDouble("longitude"));
			o.setMoment(rs.getTimestamp("moment").toInstant());
			o.setStatus(orderStatus.values()[rs.getInt("status")]);
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return o;
	}

//	private List<Product>   products = new ArrayList<>();
}
