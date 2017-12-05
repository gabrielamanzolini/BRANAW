package team.control;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import team.entity.Candidato;
import team.entity.Voto;
import team.persistence.CandidatoDao;
import team.persistence.CandidatoDaoImp;
import team.persistence.VotoDao;
import team.persistence.VotoDaoImpl;

@ManagedBean
@SessionScoped
public class ApuracaoController {

	private CandidatoDao cdao = new CandidatoDaoImp();
	private VotoDao vdao = new VotoDaoImpl();
	private List<Voto> votos = vdao.getAllVotos();
	private List<Candidato> candidatos = cdao.readMany();
	private PieChartModel pieModel2;
	private BarChartModel barModel;

	@PostConstruct
	private void init() {
		createBarModel();
		createPieModel();
		// ordena candidatos por maior voto
		Collections.sort(candidatos, new Comparator<Candidato>() {

			@Override
			public int compare(Candidato o1, Candidato o2) {
				return (int) (o2.getVotos() - o1.getVotos());
			}

		});
	}

	private void createBarModel() {
		barModel = initBarModel();

		barModel.setTitle("Votos por Estado");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Estados");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Votos");
		yAxis.setMin(0);
		yAxis.setMax(10);

	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();
		VotoController vcon = new VotoController();
		ChartSeries values = new ChartSeries();
		String[] estados = vcon.getEstados();
		values.setLabel("Estados");
		for (String est : estados) {
			int qnt = (int) vdao.contaVotoPorEstados(est);
			if (qnt > 0) {
				values.set(est, qnt);
			}

		}
		// vdao.contaVotoPorEstados(estado)

		model.addSeries(values);

		return model;
	}

	private void createPieModel() {
		createPieModel2();
	}

	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	private void createPieModel2() {
		pieModel2 = new PieChartModel();
		for (Candidato candidato : candidatos) {
			if (candidato != null) {
				String nomeCandidato = candidato.getNome();
				Number numeroVotos = vdao.contaVotos(candidato);
				pieModel2.set(nomeCandidato, numeroVotos);
			}
		}
		pieModel2.setTitle("Gráfico das eleições");
		pieModel2.setLegendPosition("e");
		pieModel2.setFill(false);
		pieModel2.setShowDataLabels(true);
		pieModel2.setDiameter(150);
	}

	public List<Voto> getVotos() {
		return votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}

	public List<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<Candidato> candidatos) {
		this.candidatos = candidatos;
	}

	public long contaVotos(Candidato candidato) {
		long numVotos = vdao.contaVotos(candidato);
		return numVotos;
	}

}
