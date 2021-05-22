package jk.pp.engg.foundations.common.domain.core.entitlements;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import jk.pp.engg.foundations.common.domain.core.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = "AppGroupRole")
@Table(name = "app_group_role")
public class AppGroupRole extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "group_pk", nullable = false)
	private Long groupPK;

	@Column(name = "roke_pk", nullable = false)
	private Long rolePK;

}
