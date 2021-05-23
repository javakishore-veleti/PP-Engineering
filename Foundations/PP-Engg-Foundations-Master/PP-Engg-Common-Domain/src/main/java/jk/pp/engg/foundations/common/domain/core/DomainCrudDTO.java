package jk.pp.engg.foundations.common.domain.core;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DomainCrudDTO<T extends BaseDomain> {

	private T domain;
	private Long pk;
	private Long parentPK;
	private int paginiationPageNo = 1;

	private Map<String, Object> ctxData = new HashMap<>();

}
