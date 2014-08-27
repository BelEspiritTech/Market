package com.essot.web.backend.dao.concrete;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.essot.web.backend.dao.IEssotDAO;
import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.backend.entity.concrete.RelatedSKUs;

public class RelatedSKUDAO implements IEssotDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	public Serializable persistEntity(IEssotEntity entity) {
		return sessionFactory.getCurrentSession().save(entity);
	}

	public IEssotEntity findEntityById(Object id) {
		return (RelatedSKUs) sessionFactory.getCurrentSession().get(RelatedSKUs.class, (Serializable)id);
	}

	public void updateEntity(IEssotEntity entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	public void deleteEntity(IEssotEntity entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}
	
	public List<IEssotEntity> readAllData() {
		String sql = "from RelatedSKUs";
		return sessionFactory.getCurrentSession().createQuery(sql).list();
	}

	public List<IEssotEntity> getFilteredListOnPrimarKey(Collection<Object> keys) {
		String sql = "FROM  RelatedSKUs r WHERE r.sku  in (:keyList)";
		return sessionFactory.getCurrentSession().createQuery(sql).setParameterList("keyList", keys).list();
	}
}
