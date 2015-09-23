package desafiolecom.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import desafiolecom.bean.Servico;
import desafiolecom.dao.ServicoDAO;

@Path("servicos")
public class ListaServicosRest {
	private ServicoDAO servicoDAO = new ServicoDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getServicos")
	public String listarServico() {
		List<Servico> lista = new ArrayList<Servico>();
		Gson json = new Gson();
		String retorno;

		lista = servicoDAO.buscaTodosServicos();
		if (lista != null) {
			retorno = json.toJson(lista);
		} else {
			retorno = json.toJson("Vazia");
		}
		return retorno;
	}
}
