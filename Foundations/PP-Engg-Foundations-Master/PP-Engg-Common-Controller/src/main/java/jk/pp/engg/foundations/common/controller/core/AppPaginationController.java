package jk.pp.engg.foundations.common.controller.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jk.pp.engg.foundations.common.core.domain.IDomain;
import jk.pp.engg.foundations.common.core.dto.DomainCrudDTO;
import jk.pp.engg.foundations.common.service.core.AppPaginationService;

public abstract class AppPaginationController<T extends IDomain, DTO extends DomainCrudDTO<T>> {

	@Autowired
	private ModelMapper modelMapper;

	private AppPaginationService<T, DTO> appPagnSvc;

	public void setAppPaginationService(AppPaginationService<T, DTO> appPagnSvc) {
		this.appPagnSvc = appPagnSvc;
	}

	@GetMapping(path = "get-paginated-data")
	@ResponseBody
	public List<DTO> getPaginatedData(@RequestParam(required = false, name = "page") Integer page,
			@RequestParam(required = false, name = "size") Integer size,
			@RequestParam(required = false, name = "sortDir") String sortDir,
			@RequestParam(required = false, name = "sort") String sort) throws Exception {

		if (page == null || page <= 0) {
			page = 1;
		}
		if (size == null || size <= 0) {
			size = 1;
		}

		List<T> domainObjs = this.appPagnSvc.getPaginatedData(page, size, sortDir, sort);
		return domainObjs.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private DTO convertToDto(T domainObj) {
		DTO domainDto = modelMapper.map(domainObj, getTypeParameterClass());
		return domainDto;
	}

	// https://stackoverflow.com/questions/75175/create-instance-of-generic-type-in-java
	@SuppressWarnings("unchecked")
	public Class<DTO> getTypeParameterClass() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType paramType = (ParameterizedType) type;
		return (Class<DTO>) paramType.getActualTypeArguments()[1];
	}

}
