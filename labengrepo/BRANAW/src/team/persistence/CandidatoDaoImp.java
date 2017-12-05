package team.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;

import team.entity.Candidato;

public class CandidatoDaoImp implements CandidatoDao{
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public CandidatoDaoImp() {
		emf = Persistence.createEntityManagerFactory("branaw");
		em = emf.createEntityManager();
		em.setProperty("hibernate.enable_lazy_load_no_trans", "true");
	}
	
	@Override
	public void save(Candidato candidato) {
		if(!em.isOpen() && !emf.isOpen()){
			emf = Persistence.createEntityManagerFactory("branaw");
			em = emf.createEntityManager();
		}
		Candidato c = em.merge(candidato);
		em.getTransaction().begin();
		em.persist( c );
		em.getTransaction().commit();
		em.clear();
		em.close();
		emf.close();
	}

	@Override
	public Candidato getCandidato(long id) {
		if(!em.isOpen() && !emf.isOpen()){
			emf = Persistence.createEntityManagerFactory("branaw");
			em = emf.createEntityManager();
		}
		Candidato candidato = em.getReference(Candidato.class, id);
		em.clear();
		//
		em.close();
		emf.close();
		return candidato;
	}

	@Override
	public List<Candidato> readMany() {
		if(!em.isOpen() && !emf.isOpen()){
			emf = Persistence.createEntityManagerFactory("branaw");
			em = emf.createEntityManager();
		}
		TypedQuery<Candidato> query = em.createQuery("select o from Candidato o", 
				Candidato.class);
		List<Candidato> candidatos = query.getResultList();
		System.out.println("tamanho da lista: " + candidatos.size());
		for (Candidato c : candidatos){
			Hibernate.initialize(c.getClass());
		}
		em.clear();
		//
		em.close();
		emf.close();
		return candidatos;
	}
	
	@Override
	public Candidato read(String nome) {
		if(!em.isOpen() && !emf.isOpen()){
			emf = Persistence.createEntityManagerFactory("branaw");
			em = emf.createEntityManager();
		}
		Candidato candidato = (Candidato) em.createQuery("SELECT c FROM Candidato c WHERE c.nome = :nome")
			    .setParameter("nome", nome)
			    .setMaxResults(1)
			    .getResultList()
			    .get(0);
		em.clear();
		em.close();
		emf.close();
		return candidato;
	}

	@Override
	public void remove(Candidato candidato) {
		if(!em.isOpen() && !emf.isOpen()){
			emf = Persistence.createEntityManagerFactory("branaw");
			em = emf.createEntityManager();
		}
		Candidato c = em.merge(candidato);
		em.getTransaction().begin();
		em.remove( c );
		em.getTransaction().commit();
		em.clear();
		em.close();
		emf.close();
	}

	@Override
	public void update(Candidato candidato) {
		if(!em.isOpen() && !emf.isOpen()){
			emf = Persistence.createEntityManagerFactory("branaw");
			em = emf.createEntityManager();
		}
		em.getTransaction().begin();
		em.merge( candidato );
		em.getTransaction().commit();
		em.clear();
		em.close();
		emf.close();
	}



}
