package com.tools.rental.admin.brand;

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

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

@Entity
@NoArgsConstructor
@Data
@Table(name = "brand_details")
public class ToolBrand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@Column(name = "brand_code", nullable = false, unique = true)
	private String brandCode;
	
	@Column(name = "name")
	private String name;
	
	
	@Column(name = "descriptions")
	private String descriptions;
		
	
	 @OneToMany
	    @JoinColumn(name="type_brand_fk") //we need to duplicate the physical information
	    public Set<ToolType> toolType;
	
	
	/* OKK This is working as Expected
	@OneToMany(mappedBy="toolBrand", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	private Set<ToolType> toolTypes;
	*/
	
	 
		////////BELOW LINES TO BE DELETED//////

	
	/** 99
	
	 @OneToOne(mappedBy = "toolBrand")
	 private ToolType toolType;
	*/
	/*
	@ManyToOne
	@JoinColumn(name="type_brand", nullable=false)
	 private ToolType toolType;
*/
	

	/*88
	@ManyToOne
	@JoinColumn(name="BRAND_ID", nullable=false)
	 private ToolType brandId;
88*/
	 
	 /*OneToMany*/
	 
	 
	 
}
