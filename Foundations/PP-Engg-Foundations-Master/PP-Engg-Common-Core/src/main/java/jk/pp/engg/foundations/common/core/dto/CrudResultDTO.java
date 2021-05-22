package jk.pp.engg.foundations.common.core.dto;

import java.util.List;

import jk.pp.engg.foundations.common.core.domain.IDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CrudResultDTO<T extends IDomain> {

	private String domainName;
	private Long pk;
	private Integer rowCount;
	private T domain;
	private List<T> domains;
	private Boolean resultSuccess = Boolean.FALSE;
	private Boolean serviceAccountUser = Boolean.FALSE;

	private String jwtToken;

	public CrudResultDTO(T domain) {
		this.domain = domain;
	}

	public CrudResultDTO(Long pk) {
		this.pk = pk;
	}

	public CrudResultDTO(List<T> domains) {
		this.domains = domains;
	}

}
