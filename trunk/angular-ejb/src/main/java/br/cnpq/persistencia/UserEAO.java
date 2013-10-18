package br.cnpq.persistencia;

import javax.ejb.Local;

import br.cnpq.model.User;

@Local
public interface UserEAO {

	public void save(User user) throws Exception;

}
