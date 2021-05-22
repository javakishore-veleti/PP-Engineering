package jk.pp.engg.foundations.common.service.core;

import java.util.List;

import jk.pp.engg.foundations.common.core.domain.IDomain;
import jk.pp.engg.foundations.common.core.dto.DomainCrudDTO;

public interface AppPaginationService<T extends IDomain, DTO extends DomainCrudDTO<T>> {

	List<T> getPaginatedData(int page, int size, String sortDir, String sort) throws Exception;

}
