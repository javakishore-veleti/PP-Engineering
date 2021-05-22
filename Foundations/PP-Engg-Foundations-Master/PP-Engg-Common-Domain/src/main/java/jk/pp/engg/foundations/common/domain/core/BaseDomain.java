package jk.pp.engg.foundations.common.domain.core;

import jk.pp.engg.foundations.common.core.domain.IDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDomain implements IDomain {

	private Long pk;

	@Override
	public String toString() {
		return "BaseDomain [pk=" + pk + "]";
	}

}
