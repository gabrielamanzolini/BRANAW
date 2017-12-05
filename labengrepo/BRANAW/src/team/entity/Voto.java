package team.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Proxy;

@Proxy(lazy=false)
@Entity
public class Voto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nomeEleitor;
	private String cpfEleitor;
	private Candidato candidatoEscolhido;
	private String estado;
	
//	public Date getDataVoto() {
//		return dataVoto;
//	}
//	public void setDataVoto(Date dataVoto) {
//		this.dataVoto = dataVoto;
//		
	public String getNomeEleitor() {
		return nomeEleitor;
	}
	public void setNomeEleitor(String nomeEleitor) {
		this.nomeEleitor = nomeEleitor;
	}
	
	@Id
	public String getCpfEleitor() {
		return cpfEleitor;
	}
	public void setCpfEleitor(String cpfEleitor) {
		this.cpfEleitor = cpfEleitor;
	}
	
	@ManyToOne (targetEntity=Candidato.class)
	@JoinColumn(name="id", nullable=false)
	public Candidato getCandidatoEscolhido() {
		return candidatoEscolhido;
	}
	public void setCandidatoEscolhido(Candidato candidatoEscolhido) {
		this.candidatoEscolhido = candidatoEscolhido;
	}
	
	@Override
	public String toString() {
		String detalhesVoto = "Nome Eleitor: " + getNomeEleitor() + "\n" 
				+ "CPF do Eleitor: " + getCpfEleitor() + "\n" 
				+ "Candidato Escolhido: " + getCandidatoEscolhido().getNome(); 
		return detalhesVoto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
