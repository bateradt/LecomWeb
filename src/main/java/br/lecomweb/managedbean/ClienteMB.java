package br.lecomweb.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import desafiolecom.bean.Cliente;
import desafiolecom.dao.ClienteDAO;

@ManagedBean
@SessionScoped
public class ClienteMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cadastro = "Cadastro de Clientes";
	private Cliente clienteCadastro = new Cliente();
	private DataModel listaClientes;
	private ClienteDAO cliDAO = new ClienteDAO();
	private String erroDao;

	/**
	 * @return the erroDao
	 */
	public String getErroDao() {
		return erroDao;
	}

	/**
	 * @param erroDao
	 *            the erroDao to set
	 */
	public void setErroDao(String erroDao) {
		this.erroDao = erroDao;
	}

	public DataModel getListarClientes() {
		List<Cliente> lista = cliDAO.buscaTodosClientes();
		listaClientes = new ListDataModel(lista);
		return listaClientes;
	}

	public String salvarCliente() {
		if (cliDAO.inserirCliente(clienteCadastro)) {
			return "index";
		} else {
			erroDao = cliDAO.erro;
			return "Erro";
		}
	}

	public String excluirCliente() {
		Cliente cliTemp = (Cliente) (listaClientes.getRowData());
		if (cliDAO.excluirCliente(cliTemp)) {
			return "index";
		} else {
			erroDao = cliDAO.erro;
			return "Erro";
		}
	}

	public String editarCliente() {
		if (cliDAO.editarCliente(clienteCadastro)) {
			return "index";
		} else {
			erroDao = cliDAO.erro;
			return "Erro";
		}
	}

	public String prepararSalvarCliente() {
		clienteCadastro = new Cliente();
		return "gerenciarCliente";
	}

	public String prepararAlterarCliente() {
		clienteCadastro = (Cliente) (listaClientes.getRowData());
		return "gerenciarCliente";
	}

	public String getCadastro() {
		return cadastro;
	}

	public void setCadastro(String cadastro) {
		this.cadastro = cadastro;
	}

	/**
	 * @return the clienteCadastro
	 */
	public Cliente getClienteCadastro() {
		return clienteCadastro;
	}

	/**
	 * @param clienteCadastro
	 *            the clienteCadastro to set
	 */
	public void setClienteCadastro(Cliente clienteCadastro) {
		this.clienteCadastro = clienteCadastro;
	}

}
