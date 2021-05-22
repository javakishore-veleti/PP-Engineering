package jk.pp.engg.foundations.common.domain.core.entitlements;

import javax.persistence.Entity;
import javax.persistence.Table;

import jk.pp.engg.foundations.common.domain.core.BaseDomainUniqueNameDesc;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity(name = "AppRole")
@Table(name = "app_role")
public class AppRole extends BaseDomainUniqueNameDesc {

	private static final long serialVersionUID = 1L;

}