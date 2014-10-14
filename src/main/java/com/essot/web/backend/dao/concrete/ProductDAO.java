package com.essot.web.backend.dao.concrete;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.essot.web.backend.dao.IEssotDAO;
import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.backend.entity.concrete.Product;

public class ProductDAO implements IEssotDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Serializable persistEntity(IEssotEntity entity) { 
		return sessionFactory.getCurrentSession().save(entity);
	}

	public IEssotEntity findEntityById(Object id) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, (Serializable)id);
	}

	public void updateEntity(IEssotEntity entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	public void deleteEntity(IEssotEntity entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}
	
	public List<IEssotEntity> readAllData() {
		String sql = "from Product";
		return sessionFactory.getCurrentSession().createQuery(sql).list();
	}

	public List<IEssotEntity> getFilteredListOnPrimarKey(Collection<Object> keys) {
		String sql = "FROM  Product p WHERE p.skuName  in (:keyList) ORDER BY p.priority";
		return sessionFactory.getCurrentSession().createQuery(sql).setParameterList("keyList", keys).list();
	}

	public List<IEssotEntity> searchOnIndexes(Collection<Object> filter) {
		String sql = "FROM  Product p WHERE p.skuName  like (:searchKey) OR p.name like (:searchKey) OR p.description like (:searchKey)";
		return sessionFactory.getCurrentSession().createQuery(sql).setParameter("searchKey", "%"+filter.toArray()[0] +"%").list();
	}
	
	
}
