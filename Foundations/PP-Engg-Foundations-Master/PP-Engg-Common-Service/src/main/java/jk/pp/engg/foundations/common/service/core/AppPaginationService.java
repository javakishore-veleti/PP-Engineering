package jk.pp.engg.foundations.common.service.core;

import java.util.List;

import jk.pp.engg.foundations.common.domain.core.BaseDomain;
import jk.pp.engg.foundations.common.domain.core.DomainCrudDTO;

public interface AppPaginationService<T extends BaseDomain, DTO extends DomainCrudDTO<T>> {

	List<T> getPaginatedData(int page, int size, String sortDir, String sort) throws Exception;

}
