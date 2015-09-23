package desafiolecom.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "ordemservico")
@Proxy(lazy = false)
public class OrdemServico extends Persistivel implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FK_CLI_ID", referencedColumnName = "id", nullable = false, unique = true)
	private Cliente clientes;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FK_SER_ID", referencedColumnName = "id", nullable = false, unique = true)
	private Servico servicos;

	@Column(name = "os_datainicial")
	private Date dataInicial;

	@Column(name = "os_datafinal")
	private Date dataFinal;

	private int diasAteFinal;

	/**
	 * @return the clientes
	 */
	public Cliente getClientes() {
		return clientes;
	}

	/**
	 * @param clientes
	 *            the clientes to set
	 */
	public void setClientes(Cliente clientes) {
		this.clientes = clientes;
	}

	/**
	 * @return the diasAteFinal
	 */
	public int getDiasAteFinal() {
		/*
		 * if ((dataFinal != null) && (dataInicial != null)) { DiasAteFinal =
		 * dataDiff(dataInicial, dataFinal); }
		 */
		return diasAteFinal;
	}

	/**
	 * @param diasAteFinal
	 *            the diasAteFinal to set
	 */
	public void setDiasAteFinal(int diasAteFinal) {
		this.diasAteFinal = diasAteFinal;
		/*
		 * if ((dataFinal != null) && (dataInicial != null)) { DiasAteFinal =
		 * dataDiff(dataInicial, dataFinal); }
		 */
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return clientes;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente clientes) {
		this.clientes = clientes;
	}

	/**
	 * @return the servicos
	 */
	public Servico getServicos() {
		return servicos;
	}

	/**
	 * @param servicos
	 *            the servicos to set
	 */
	public void setServicos(Servico servicos) {
		this.servicos = servicos;
	}

	/**
	 * @return the dataInicial
	 */
	public Date getDataInicial() {
		return dataInicial;
	}

	/**
	 * @param dataInicial
	 *            the dataInicial to set
	 */
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	/**
	 * @return the dataFinal
	 */
	public Date getDataFinal() {
		return dataFinal;
	}

	/**
	 * @param dataFinal
	 *            the dataFinal to set
	 */
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public static int dataDiff(java.util.Date dataLow, java.util.Date dataHigh) {

		GregorianCalendar startTime = new GregorianCalendar();
		GregorianCalendar endTime = new GregorianCalendar();

		GregorianCalendar curTime = new GregorianCalendar();
		GregorianCalendar baseTime = new GregorianCalendar();

		startTime.setTime(dataLow);
		endTime.setTime(dataHigh);

		int dif_multiplier = 1;

		// Verifica a ordem de inicio das datas
		if (dataLow.compareTo(dataHigh) < 0) {
			baseTime.setTime(dataHigh);
			curTime.setTime(dataLow);
			dif_multiplier = 1;
		} else {
			baseTime.setTime(dataLow);
			curTime.setTime(dataHigh);
			dif_multiplier = -1;
		}

		int result_years = 0;
		int result_months = 0;
		int result_days = 0;

		// Para cada mes e ano, vai de mes em mes pegar o ultimo dia para import
		// acumulando
		// no total de dias. Ja leva em consideracao ano bissesto
		while (curTime.get(GregorianCalendar.YEAR) < baseTime
				.get(GregorianCalendar.YEAR)
				|| curTime.get(GregorianCalendar.MONTH) < baseTime
						.get(GregorianCalendar.MONTH)) {

			int max_day = curTime
					.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
			result_months += max_day;
			curTime.add(GregorianCalendar.MONTH, 1);

		}

		// Marca que é um saldo negativo ou positivo
		result_months = result_months * dif_multiplier;

		// Retirna a diferenca de dias do total dos meses
		result_days += (endTime.get(GregorianCalendar.DAY_OF_MONTH) - startTime
				.get(GregorianCalendar.DAY_OF_MONTH));

		return result_years + result_months + result_days;
	}

}
