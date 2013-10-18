package br.cnpq.persistencia.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.cnpq.model.User;
import br.cnpq.persistencia.UniversalEAO;
import br.cnpq.persistencia.UserEAO;

@Stateless
public class UserEAOImpl implements UserEAO {

	@EJB
	private UniversalEAO universalEAO;

	@Override
	public void save(User user) throws Exception {
		universalEAO.save(user);
	}

}
