package jk.pp.engg.foundations.common.domain.core;

import jk.pp.engg.foundations.common.core.domain.IDomainNameDesc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BaseDomainNameDesc extends BaseDomain implements IDomainNameDesc {

	private String name;
	private String description;

}
