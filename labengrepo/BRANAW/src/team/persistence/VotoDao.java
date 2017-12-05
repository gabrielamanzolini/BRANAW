package team.persistence;

import java.util.List;

import team.entity.Candidato;
import team.entity.Voto;

public interface VotoDao {
	public void save(Voto voto);
	public List<Voto> getAllVotos();
	public long contaVotos(Candidato candidato);
	public long contaVotoPorEstados(String estado);
	public void removeVotos(long candidatoId);
}
