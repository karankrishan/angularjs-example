package br.cnpq.servico;

import java.util.List;

import javax.ejb.Local;

import br.cnpq.model.User;

@Local
public interface UserServiceLocal {

	public void save(User user) throws Exception;

	public void delete(User user) throws Exception;

	public List<User> listAll() throws Exception;

}
