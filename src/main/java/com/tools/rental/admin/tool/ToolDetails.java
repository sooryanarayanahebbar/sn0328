package com.tools.rental.admin.tool;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;

import com.tools.rental.admin.brand.ToolBrand;
import com.tools.rental.admin.type.ToolType;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.OneToMany;
/*
 * @Author: Soorya
 * */



@Entity
@NoArgsConstructor
@Data
@Table(name = "tool_details")
public class ToolDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	////@EmbeddedId
    ////private ToolDetailId toolDetailId;
	
	@Column(name = "tool_code", nullable = false, unique = true)
	private String toolCode;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "total_units")
	private String totalUnits;
	
	@Column(name = "available_units")
	private String availableUnits;
	
	
	@Column(name = "status")
	private boolean status;
	

	//@JsonBackReference
	//@JsonManagedReference
   /* @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tool_type")
    private ToolType toolType;
    
     */
    
	@ManyToOne
    @JoinColumn(name="tool_type_fk", insertable=false, updatable=false)
    public ToolType toolType;
	
	/* TO BE DELETED
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "tool_type", referencedColumnName = "id") private ToolType
	 * toolType;
	 */
	
    
    //IF Tool is not UNIQUE below lines are PERFECT
	////@ManyToOne
   //// @JoinColumn(name="TOOL_TYPE_FK", insertable=false, updatable=false)
    ////public ToolType toolType;
	
	
	/* OKK This is working as Expected
	@ManyToOne
	@JoinColumn(name="TOOL_TYPE_ID", nullable=false)
	 private ToolType toolType;
	
	 */
	
	
	////////BELOW LINES TO BE DELETED//////
 
	/** 99
	 * 
	 
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tool_type", referencedColumnName = "id")
    private ToolType toolType;
*/

    /*
	@Column(name = "brand_code")
	private String brandCode;
	
	@Column(name = "type_code")
	private String typeCode; */
	
	
	//@JoinColumn(name = "toolCode")
	
	/*
	@OneToMany(mappedBy="id", 
			fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	private Set<ToolType> toolType  = new HashSet<ToolType>();
	
	*/
	/*88
	@Column(name = "TYPE_ID")
	private Integer typeId;
	
	@OneToMany(mappedBy="typeId", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	private Set<ToolType> toolTypes  = new HashSet<ToolType>();
	88*/
}
