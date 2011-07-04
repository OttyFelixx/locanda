package service;

import java.util.List;

import model.listini.Convention;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ConventionService {
	
	public Integer insertConvention(Convention convention);
	public Integer updateConvention(Convention convention);
	public Integer deleteConvention(Integer id);
	public List<Convention> findConventionsByIdStructure(Integer id_structure);
	public Convention findConventionById(Integer id);

}
