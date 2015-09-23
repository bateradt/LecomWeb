package desafiolecom.dao;

import java.util.List;

import org.hibernate.Session;

import desafiolecom.bean.Cliente;
import desafiolecom.db.HibernateUtil;

public class ClienteDAO extends HibernateUtil {

	public String erro;

	public boolean inserirCliente(Cliente cliente) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(cliente);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			erro = e.getMessage();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	public boolean editarCliente(Cliente cliente) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(cliente);
			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			erro = e.getMessage();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	public boolean excluirCliente(Cliente cliente) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(cliente);
			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			erro = e.getMessage();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> buscaTodosClientes() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			org.hibernate.Query pesquisa = session.createQuery("FROM Cliente ");
			List<Cliente> list = (List<Cliente>) pesquisa.list();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			erro = e.getMessage();
			return null;
		} finally {
			session.close();
		}
	}

}
