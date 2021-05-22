package jk.pp.engg.foundations.common.service.core;

import jk.pp.engg.foundations.common.core.domain.IDomain;
import jk.pp.engg.foundations.common.core.dto.CrudResultDTO;
import jk.pp.engg.foundations.common.core.dto.DomainCrudDTO;

public interface AppCrudService<T extends IDomain, DTO extends DomainCrudDTO<T>> {

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
