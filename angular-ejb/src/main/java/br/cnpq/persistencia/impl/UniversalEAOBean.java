package br.cnpq.persistencia.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.cnpq.persistencia.UniversalEAO;

@Stateless
public class UniversalEAOBean implements UniversalEAO {

	@PersistenceContext
	private EntityManager	entityManager;

	@PersistenceContext
	private Session			session;

	@Override
	public Object get(Class clazz, Serializable id) {
		return getSession().get(clazz, id);
	}

	@Override
	public List getAll(Class clazz) {
		Criteria criteria = getSession().createCriteria(clazz);
		return criteria.list();
	}

	@Override
	public Object save(Object o) throws Exception {
		getSession().saveOrUpdate(o);

		return o;
	}

	@Override
	public Object merge(Object o) throws Exception {
		getSession().merge(o);

		return o;
	}

	@Override
	public void remove(Class clazz, Serializable id) throws Exception {
		getSession().delete(get(clazz, id));

	}

	public Session getSession() {
		return (Session) this.getEntityManager().getDelegate();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public Session getCurrentSession() throws Exception {
		return getSession();
	}

}
