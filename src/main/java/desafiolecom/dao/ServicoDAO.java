package desafiolecom.dao;

import java.util.List;

import org.hibernate.Session;

import desafiolecom.bean.Servico;
import desafiolecom.db.HibernateUtil;

public class ServicoDAO extends HibernateUtil {

	public String erro;

	public boolean inserirServico(Servico servico) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(servico);
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

	public boolean editarServico(Servico servico) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(servico);
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

	public boolean excluirServico(Servico servico) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(servico);
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
	public List<Servico> buscaTodosServicos() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			org.hibernate.Query pesquisa = session.createQuery("FROM Servico ");
			List<Servico> list = (List<Servico>) pesquisa.list();
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
