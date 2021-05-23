package jk.pp.engg.foundations.common.domain.core.entitlements;

import javax.persistence.Entity;
import javax.persistence.Table;

import jk.pp.engg.foundations.common.domain.core.BaseDomainUniqueNameDesc;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = "AppGroup")
@Table(name = "app_group")
public class AppGroup extends BaseDomainUniqueNameDesc {

	private static final long serialVersionUID = 1L;

}
