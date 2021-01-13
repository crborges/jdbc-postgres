package entities;	

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Order {
	
	
	private Long			id;
	private Double 			latitude;
	private Double 			longitude;
	private Instant			moment;
	private orderStatus 	status;
	private List<Product>   products = new ArrayList<>();
	
	public Order() {	}
	
	public void setId(Long id) 							{	this.id = id;					}
	public void setLatitude(Double latitude) 			{	this.latitude = latitude;		}
	public void setLongitude(Double longitude) 			{	this.longitude = longitude;		}
	public void setMoment(Instant moment) 				{	this.moment = moment;			}
	public void setStatus(orderStatus status) 			{	this.status = status;			}
	public void setProducts(List<Product> products) 	{	this.products = products;		}
	
	public Long getId() 								{	return id;						}
	public Double getLatitude() 						{	return latitude;				}
	public Double getLongitude()						{	return longitude;				}
	public Instant getMoment() 							{	return moment;					}
	public orderStatus getStatus() 						{	return status;					}
	public List<Product> getProducts()					{	return products;				}

	@Override
	public String toString() {
		return "Order [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", moment=" + moment
				+ ", status=" + status + ", products=" + products + "]";
	}
	

	
	
}
