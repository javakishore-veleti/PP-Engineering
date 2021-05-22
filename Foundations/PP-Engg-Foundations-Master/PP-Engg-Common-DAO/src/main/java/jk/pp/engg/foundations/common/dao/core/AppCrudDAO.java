package jk.pp.engg.foundations.common.dao.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import jk.pp.engg.foundations.common.core.domain.IDomain;
import jk.pp.engg.foundations.common.core.dto.CrudResultDTO;
import jk.pp.engg.foundations.common.core.dto.DomainCrudDTO;

@NoRepositoryBean
public interface AppCrudDAO<T extends IDomain> extends CrudRepository<T, Long> {

	default CrudResultDTO<T> create(DomainCrudDTO<T> crudDTO) throws Exception {
		T domain = this.save(crudDTO.getDomain());

		CrudResultDTO<T> result = new CrudResultDTO<T>(domain);
		result.setPk(domain.getPk());
		result.setDomain(domain);

		return result;
	}

	default CrudResultDTO<T> update(DomainCrudDTO<T> crudDTO) throws Exception {
		T domain = this.save(crudDTO.getDomain());

		CrudResultDTO<T> result = new CrudResultDTO<T>(domain);
		result.setPk(domain.getPk());
		result.setDomain(domain);

		return result;
	}

	default CrudResultDTO<T> deleteByPK(Long pk) throws Exception {
		this.deleteById(pk);

		CrudResultDTO<T> result = new CrudResultDTO<T>();
		result.setPk(pk);

		return result;
	}

	default CrudResultDTO<T> deleteAll(DomainCrudDTO<T> crudDTO) throws Exception {
		this.deleteAll();

		CrudResultDTO<T> result = new CrudResultDTO<T>();

		return result;
	}

	default CrudResultDTO<T> findByPK(Long pk) throws Exception {
		T domain = this.findById(pk).orElseGet(null);

		CrudResultDTO<T> result = new CrudResultDTO<T>(domain);
		result.setPk(pk);

		if (domain != null) {
			result.setDomain(domain);
			result.setDomainName(domain.getClass().getCanonicalName());
		}

		return result;
	}

	default CrudResultDTO<T> getAll(DomainCrudDTO<T> crudDTO) throws Exception {
		Iterable<T> domains = this.findAll();

		List<T> domainsList = new ArrayList<T>();
		domains.forEach(domainsList::add);

		CrudResultDTO<T> result = new CrudResultDTO<T>(domainsList);

		return result;
	}

	default CrudResultDTO<T> getAll(DomainCrudDTO<T> crudDTO, PageRequest pageRequest) throws Exception {
		Iterable<T> domains = this.findAll();

		List<T> domainsList = new ArrayList<T>();
		domains.forEach(domainsList::add);

		CrudResultDTO<T> result = new CrudResultDTO<T>(domainsList);

		return result;
	}

	default CrudResultDTO<T> getAllByCriteria(DomainCrudDTO<T> crudDTO) throws Exception {
		Iterable<T> domains = this.findAll();

		List<T> domainsList = new ArrayList<T>();
		domains.forEach(domainsList::add);

		CrudResultDTO<T> result = new CrudResultDTO<T>(domainsList);

		return result;
	}

	default long getDomainCount() {
		return this.count();
	}

}
