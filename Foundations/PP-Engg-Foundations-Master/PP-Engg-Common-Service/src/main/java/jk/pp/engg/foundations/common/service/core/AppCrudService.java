package jk.pp.engg.foundations.common.service.core;

import jk.pp.engg.foundations.common.domain.core.BaseDomain;
import jk.pp.engg.foundations.common.domain.core.CrudResultDTO;
import jk.pp.engg.foundations.common.domain.core.DomainCrudDTO;

public interface AppCrudService<T extends BaseDomain, DTO extends DomainCrudDTO<T>> {

	default String getDomainClassName() {
		return null;
	}

	CrudResultDTO<T> create(DTO crudDTO) throws Exception;

	CrudResultDTO<T> update(DTO crudDTO) throws Exception;

	CrudResultDTO<T> deleteByPK(Long pkO) throws Exception;

	CrudResultDTO<T> deleteAll(DTO crudDTO) throws Exception;

	CrudResultDTO<T> findByPK(Long pk) throws Exception;

	CrudResultDTO<T> getAll(DTO crudDTO) throws Exception;

	CrudResultDTO<T> getAllByCriteria(DTO crudDTO) throws Exception;

	long getDomainCount() throws Exception;

}
