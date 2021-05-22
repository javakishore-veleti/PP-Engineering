package jk.pp.engg.foundations.common.domain.core.entitlements;

import javax.persistence.Entity;
import javax.persistence.Table;

import jk.pp.engg.foundations.common.core.domain.entitlements.IAppGroup;
import jk.pp.engg.foundations.common.domain.core.BaseDomainUniqueNameDesc;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity(name = "AppGroup")
@Table(name = "app_group")
public class AppGroup extends BaseDomainUniqueNameDesc implements IAppGroup {

	private static final long serialVersionUID = 1L;

}
