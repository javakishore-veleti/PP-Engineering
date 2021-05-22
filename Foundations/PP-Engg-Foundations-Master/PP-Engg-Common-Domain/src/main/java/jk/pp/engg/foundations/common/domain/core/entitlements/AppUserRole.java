package jk.pp.engg.foundations.common.domain.core.entitlements;

import javax.persistence.Entity;
import javax.persistence.Table;

import jk.pp.engg.foundations.common.core.domain.entitlements.IAppUserRole;
import jk.pp.engg.foundations.common.domain.core.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = "AppUserRole")
@Table(name = "app_user_role")
public class AppUserRole extends BaseDomain implements IAppUserRole {

	private static final long serialVersionUID = 1L;

	private Long userPK;
	private Long rolePK;

}
