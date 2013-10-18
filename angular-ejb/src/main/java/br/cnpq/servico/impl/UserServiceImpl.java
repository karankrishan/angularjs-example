package br.cnpq.servico.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.LocalBinding;

import br.cnpq.model.User;
import br.cnpq.persistencia.UniversalEAO;
import br.cnpq.servico.UserServiceLocal;

@Stateless
@LocalBinding(jndiBinding = "UserService")
public class UserServiceImpl implements UserServiceLocal {

	@EJB
	private UniversalEAO universalEAO;

	@Override
	public void save(User user) throws Exception {
		universalEAO.save(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listAll() throws Exception {
		return universalEAO.getAll(User.class);

	}

	@Override
	public void delete(User user) throws Exception {
		universalEAO.remove(user.getClass(), user.getId());
	}

}
