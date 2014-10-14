package com.essot.web.backend.dao.concrete;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.essot.web.backend.dao.IEssotDAO;
import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.backend.entity.concrete.ProductXENCode;

public class ProductXEnCodeDAO implements IEssotDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Serializable persistEntity(IEssotEntity entity) { 
		return sessionFactory.getCurrentSession().save(entity);
	}

	public IEssotEntity findEntityById(Object id) {
		return (ProductXENCode) sessionFactory.getCurrentSession().get(ProductXENCode.class, (Serializable)id);
	}

	public void updateEntity(IEssotEntity entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	public void deleteEntity(IEssotEntity entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}
	
	public List<IEssotEntity> readAllData() {
		String sql = "from ProductXENCode";
		return sessionFactory.getCurrentSession().createQuery(sql).list();
	}

	public List<IEssotEntity> getFilteredListOnPrimarKey(Collection<Object> keys) {
		String sql = "FROM  ProductXENCode p WHERE p.skuName  in (:keyList)";
		return sessionFactory.getCurrentSession().createQuery(sql).setParameterList("keyList", keys).list();
	}

	public List<IEssotEntity> searchOnIndexes(Collection<Object> filter) {
		return null;
	}

}
