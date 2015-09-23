package desafiolecom.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import desafiolecom.bean.OrdemServico;
import desafiolecom.db.HibernateUtil;

public class OrdemServicoDAO extends HibernateUtil {

	@SuppressWarnings("deprecation")
	public static double retornaDiasParaFimDaOS(Date datafinal) {
		double dias = 0;
		Date dataAtual = new Date();
		long diferenca = datafinal.getTime() - dataAtual.getTime();
		double diferencaDias = (diferenca / 1000) / 60 / 60 / 24;
		long horasRestantes = (diferenca / 1000) / 60 / 60 % 24;
		dias = diferencaDias + (horasRestantes / 24d);

		return dias;

	}

	public boolean inserirOrdemServico(OrdemServico os) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(os);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	public boolean editarOrdemServico(OrdemServico os) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.merge(os);
			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	public boolean excluirOrdemServico(OrdemServico os) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(os);
			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<OrdemServico> buscaTodasOrdemServico() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			org.hibernate.Query pesquisa = session
					.createQuery("FROM OrdemServico ");
			List<OrdemServico> list = (List<OrdemServico>) pesquisa.list();
			List<OrdemServico> listaAux = new ArrayList<OrdemServico>();
			Date data = new Date();
			for (OrdemServico os : list) {
				int diasAteFinal = os.dataDiff(data, os.getDataFinal());
				os.setDiasAteFinal(diasAteFinal);
				listaAux.add(os);
			}
			return listaAux;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

}
