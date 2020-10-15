package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_PURCHASE_ORDER")
public class BillingPurchaseOrder implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name = "PO_ID")
	private Integer poid;
	
	@Column(name = "PO_NUMBER")
	private Integer poNumber;
	
	@Column(name = "ALLOC_ID")
	private long billingallocId;

	@Column(name = "PO_DATE")
	private Date poDate;

	@Column(name = "QUANTITY")
	private long poQantity;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "UPDATED_BY")
	private String updatdBy;

	@Column(name = "UPDATED_DATE")
	private Date updatdDate;

	

}
