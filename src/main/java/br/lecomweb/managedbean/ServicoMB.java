package br.lecomweb.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import desafiolecom.bean.Servico;
import desafiolecom.dao.ServicoDAO;

@ManagedBean
@SessionScoped
public class ServicoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cadastro = "Cadastro de Serviços";
	private Servico servicoCadastro = new Servico();
	private DataModel listaServicos;
	private ServicoDAO serDAO = new ServicoDAO();
	private String erroDao;

	// private Long id;

	public DataModel getListarServicos() {
		List<Servico> lista = serDAO.buscaTodosServicos();
		listaServicos = new ListDataModel(lista);
		return listaServicos;
	}

	public String salvarServico() {
		if (serDAO.inserirServico(servicoCadastro)) {
			return "servicos";
		} else {
			erroDao = serDAO.erro;
			return "Erro";
		}
	}

	public String excluirServico() {
		Servico serTemp = (Servico) (listaServicos.getRowData());
		if (serDAO.excluirServico(serTemp)) {
			return "servicos";
		} else {
			erroDao = serDAO.erro;
			return "Erro";
		}
	}

	public String editarServico() {
		// servicoCadastro.setId(id);
		if (serDAO.editarServico(servicoCadastro)) {
			return "servicos";
		} else {
			erroDao = serDAO.erro;
			return "Erro";
		}
	}

	public String prepararSalvarServico() {
		servicoCadastro = new Servico();
		return "gerenciarServico";
	}

	public String prepararAlterarServico() {
		servicoCadastro = (Servico) (listaServicos.getRowData());
		// id = servicoCadastro.getId();
		return "gerenciarServico";
	}

	public Servico getServicoCadastro() {
		return servicoCadastro;
	}

	public void setServicoCadastro(Servico servicoCadastro) {
		this.servicoCadastro = servicoCadastro;
	}

	public String getErroDao() {
		return erroDao;
	}

	public void setErroDao(String erroDao) {
		this.erroDao = erroDao;
	}

}
