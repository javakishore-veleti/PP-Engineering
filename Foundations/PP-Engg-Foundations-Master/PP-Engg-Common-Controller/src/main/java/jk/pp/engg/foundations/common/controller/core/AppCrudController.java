package jk.pp.engg.foundations.common.controller.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jk.pp.engg.foundations.common.domain.core.BaseDomain;
import jk.pp.engg.foundations.common.domain.core.CrudResultDTO;
import jk.pp.engg.foundations.common.domain.core.DomainCrudDTO;
import jk.pp.engg.foundations.common.service.core.AppCrudService;

public abstract class AppCrudController<T extends BaseDomain, DTO extends DomainCrudDTO<T>> {

	private AppCrudService<T, DTO> crudService;

	public void setCrudService(AppCrudService<T, DTO> crudService) {
		this.crudService = crudService;
	}

	@RequestMapping(path = "create", method = RequestMethod.POST)
	public ResponseEntity<CrudResultDTO<T>> create(@RequestBody DTO crudDTO) throws Exception {

		CrudResultDTO<T> result = this.crudService.create(crudDTO);
		return ResponseEntity.ok(result);
	}

	@RequestMapping(path = "update", method = RequestMethod.POST)
	public ResponseEntity<CrudResultDTO<T>> update(@RequestBody DTO crudDTO) throws Exception {
		CrudResultDTO<T> result = this.crudService.update(crudDTO);
		return ResponseEntity.ok(result);
	}

	@RequestMapping(path = "delete-by-pk", method = RequestMethod.POST)
	public ResponseEntity<CrudResultDTO<T>> deleteByPK(@PathVariable Long pk) throws Exception {
		CrudResultDTO<T> result = this.crudService.deleteByPK(pk);
		return ResponseEntity.ok(result);
	}

	@RequestMapping(path = "delete-all", method = RequestMethod.POST)
	public ResponseEntity<CrudResultDTO<T>> deleteAll() throws Exception {
		CrudResultDTO<T> result = this.crudService.deleteAll(null);
		return ResponseEntity.ok(result);
	}

	@RequestMapping(path = "find-by-pk/{pk}", method = RequestMethod.GET)
	public ResponseEntity<CrudResultDTO<T>> findByPK(@PathVariable Long pk) throws Exception {
		CrudResultDTO<T> result = this.crudService.findByPK(pk);
		return ResponseEntity.ok(result);
	}

	@RequestMapping(path = "get-all", method = RequestMethod.GET)
	public ResponseEntity<CrudResultDTO<T>> getAll() throws Exception {
		CrudResultDTO<T> result = this.crudService.getAll(null);
		return ResponseEntity.ok(result);
	}

	@RequestMapping(path = "get-all-by-criteria", method = RequestMethod.POST)
	public ResponseEntity<CrudResultDTO<T>> getAllByCriteria(@RequestBody DTO crudDTO) throws Exception {
		CrudResultDTO<T> result = this.crudService.getAllByCriteria(crudDTO);
		return ResponseEntity.ok(result);
	}

	@RequestMapping(path = "populate-initial-data", method = RequestMethod.GET)
	public ResponseEntity<CrudResultDTO<T>> populateInitialData() throws Exception {
		List<DTO> domains = populateInitialDataDomais();

		domains.stream().allMatch(aDomainDTO -> {
			try {
				this.crudService.create(aDomainDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		});

		CrudResultDTO<T> result = new CrudResultDTO<T>();
		result.setRowCount(domains.size());
		result.setDomainName(this.crudService.getDomainClassName());

		return ResponseEntity.ok(result);
	}

	public List<DTO> populateInitialDataDomais() {
		return new ArrayList<DTO>();
	}
}
