package team.control;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import team.entity.Candidato;
import team.persistence.CandidatoDao;
import team.persistence.CandidatoDaoImp;

@ManagedBean
@SessionScoped
public class ImagesView {

	public StreamedContent getImagem() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
        	CandidatoDao cdao = new CandidatoDaoImp();
            String candidatoId = context.getExternalContext().getRequestParameterMap().get("candidatoId");
            Candidato candidato = cdao.getCandidato(Long.valueOf(candidatoId));
            if(candidato.getFoto() != null){
            	return new DefaultStreamedContent(new ByteArrayInputStream(candidato.getFoto()));
            }else{
            	return new DefaultStreamedContent();
            }
         
        }
    }

	public StreamedContent getImagemVice() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			// So, we're rendering the HTML. Return a stub StreamedContent so
			// that it will generate right URL.
			return new DefaultStreamedContent();
		} else {
			// So, browser is requesting the image. Return a real
			// StreamedContent with the image bytes.
			CandidatoDao cdao = new CandidatoDaoImp();
			String candidatoId = context.getExternalContext().getRequestParameterMap().get("candidatoId");
			Candidato candidato = cdao.getCandidato(Long.valueOf(candidatoId));
			if(candidato.getViceFoto() != null){
            	return new DefaultStreamedContent(new ByteArrayInputStream(candidato.getViceFoto()));
            }else{
            	return new DefaultStreamedContent();
            }
		}
	}

}
