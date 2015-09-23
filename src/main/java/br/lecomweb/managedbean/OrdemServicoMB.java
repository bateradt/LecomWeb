package br.lecomweb.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import desafiolecom.bean.OrdemServico;
import desafiolecom.dao.OrdemServicoDAO;

@ManagedBean
@ViewScoped
public class OrdemServicoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private OrdemServico ordemservico = new OrdemServico();
	private OrdemServicoDAO osDAO = new OrdemServicoDAO();
	private DataModel listaOrdemServicos;
	private String erroDao;

	public DataModel getListarOrdemServicos() {
		List<OrdemServico> lista = osDAO.buscaTodasOrdemServico();
		listaOrdemServicos = new ListDataModel(lista);
		return listaOrdemServicos;
	}

	public OrdemServico getOrdemservico() {
		return ordemservico;
	}

	public void setOrdemservico(OrdemServico ordemservico) {
		this.ordemservico = ordemservico;
	}

	public String getErroDao() {
		return erroDao;
	}

	public void setErroDao(String erroDao) {
		this.erroDao = erroDao;
	}

}
