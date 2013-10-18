package br.cnpq.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import org.hibernate.Session;

@Local
public interface UniversalEAO {

	Object get(Class clazz, Serializable id);

	List getAll(Class clazz);

	Object save(Object o) throws Exception;

	Object merge(Object o) throws Exception;

	void remove(Class clazz, Serializable id) throws Exception;

	/**
	 * Devolve a sessao corrente.
	 * 
	 * @return
	 * @throws Exception
	 */
	Session getCurrentSession() throws Exception;

}
