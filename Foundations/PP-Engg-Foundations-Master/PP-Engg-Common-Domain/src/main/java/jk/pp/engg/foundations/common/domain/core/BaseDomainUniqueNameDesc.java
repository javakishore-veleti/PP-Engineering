package jk.pp.engg.foundations.common.domain.core;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import jk.pp.engg.foundations.common.core.domain.DomainUniqueNameDesc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BaseDomainUniqueNameDesc extends BaseDomain implements DomainUniqueNameDesc {

	private static final long serialVersionUID = 1L;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "description", length = 1000, nullable = true)
	private String description;
}
