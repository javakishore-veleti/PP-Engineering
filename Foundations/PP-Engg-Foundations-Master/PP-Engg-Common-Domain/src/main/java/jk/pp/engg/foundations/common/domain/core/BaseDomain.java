package jk.pp.engg.foundations.common.domain.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jk.pp.engg.foundations.common.core.domain.IDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDomain implements IDomain, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pk;

	@Column(name = "status", length = 25, nullable = true)
	private String status;

	@CreatedDate
	@Column(name = "created_dt")
	public Date creationDate;

	@CreatedBy
	@Column(name = "created_by")
	public Long creationBy;

	@LastModifiedDate
	@Column(name = "updated_dt")
	public Date updatedDate;

	@LastModifiedBy
	@Column(name = "updated_by")
	public Long updatedBy;

	@Override
	public String toString() {
		return "BaseDomain [pk=" + pk + "]";
	}

}
