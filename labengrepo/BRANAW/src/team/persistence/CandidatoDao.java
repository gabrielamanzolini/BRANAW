package team.persistence;

import java.util.List;

import team.entity.Candidato;

public interface CandidatoDao {
	public void save(Candidato candidato);
	public Candidato getCandidato(long id);
	public void remove(Candidato candidato);
	public void update(Candidato candidato);
	public Candidato read(String nome);
	public List<Candidato> readMany();
}
