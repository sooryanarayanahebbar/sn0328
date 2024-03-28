package com.tools.rental.product;

import com.tools.rental.admin.tool.ToolDetails;
import com.tools.rental.admin.type.ToolType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {

	private String toolCode;
	private String toolName;
	private String availableUnits;
	private String totalUnits;
	
	private String typeCode;
	private String typeName;
	private float amountPerDay;
	private int weekendDiscount;
	private int holidayDiscount;
	
	private String brandCode;
	private String brandName;
	
	private String name;
	
	
	private String descriptions;
		
	
	 
}
