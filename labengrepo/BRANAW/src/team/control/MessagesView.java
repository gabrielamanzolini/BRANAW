package team.control;

	import javax.faces.application.FacesMessage;
	import javax.faces.bean.ManagedBean;
	import javax.faces.context.FacesContext;
	 
	@ManagedBean
	public class MessagesView {
	     
	    public void info(String message) {
	        FacesContext.getCurrentInstance().addMessage(null, 
	        		new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));
	    }
	     
	    public void warn(String message) {
	        FacesContext.getCurrentInstance().addMessage(null, 
	        		new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", message));
	    }
	     
	    public void error(String message) {
	        FacesContext.getCurrentInstance().addMessage(null, 
	        		new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", message));
	    }
	     
	    public void fatal(String message) {
	        FacesContext.getCurrentInstance().addMessage(null, 
	        		new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", message));
	    }
	}
