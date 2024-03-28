package com.tools.rental.admin.type;

import java.util.HashSet;
import java.util.Set;

import com.tools.rental.admin.brand.ToolBrand;
//import com.tools.rental.admin.tool.ToolDetailId;
import com.tools.rental.admin.tool.ToolDetails;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@NoArgsConstructor
@Data
@Table(name = "type_details")
public class ToolType {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	////@EmbeddedId
    ////private ToolTypeId toolTypeId;
	
	@Column(name = "type_code")
	private String typeCode;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "tool_amount_perday")
	private Double amount;
	
	@Column(name = "weekend_discount_per")
	private Integer weekendDiscountInPercentage;
	
	
	@Column(name = "holiday_discount_per")
	private Integer holidayDiscountInPercentage;
	
	
	//@JsonManagedReference
	//@JsonBackReference
	  //@OneToOne(mappedBy = "toolType", cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
	 // private ToolDetails toolDetails;
	 
	@OneToMany
    @JoinColumn(name="tool_type_fk") //we need to duplicate the physical information
    public Set<ToolDetails> toolDetails;

    
	 //IF Tool is not UNIQUE below lines are PERFECT
	////@OneToMany
   //// @JoinColumn(name="TOOL_TYPE_FK") //we need to duplicate the physical information
    ////public Set<ToolDetails> toolDetails;
	
	
	@ManyToOne
    @JoinColumn(name="type_brand_fk", insertable=false, updatable=false)
	public ToolBrand toolBrand;
	
	
	//
	/* OKK - This is working as Expected
	@OneToMany(mappedBy="toolType", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	private Set<ToolDetails> toolDetails;
	
	//
	@ManyToOne
	@JoinColumn(name="TYPE_BRAND_ID", nullable=false)
	 private ToolBrand toolBrand;
	
	*/
	
	////////BELOW LINES TO BE DELETED//////

	/* 99
	//Tool-Type
	 @OneToOne(mappedBy = "toolType")
	 private ToolDetails toolDetails;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tool_brand", referencedColumnName = "id")
    private ToolBrand toolBrand;
    
    **/
	/*88
	@Column(name = "BRAND_ID")
	private Integer brandId;
	
	@OneToMany(mappedBy="brandId", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	private Set<ToolBrand> toolBrand  = new HashSet<ToolBrand>();
	
	//
	@ManyToOne
	@JoinColumn(name="TYPE_ID", nullable=false)
	 private ToolDetails typeId;
   88*/

	
	/*
	@ManyToOne
	@JoinColumn(name="tool_type", nullable=false)
	 private ToolDetails toolDetails;


	
	@OneToMany(mappedBy="id", 
			fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	private Set<ToolBrand> toolBrand  = new HashSet<ToolBrand>();
	*/
	
	
	
	//@OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	//@JoinColumn(name = "user_id")
	//private Set<ToolBrand> users  = new HashSet<>();
	
	
}
