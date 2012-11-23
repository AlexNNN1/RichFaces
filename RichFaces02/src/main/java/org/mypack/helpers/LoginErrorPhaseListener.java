package org.mypack.helpers;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class LoginErrorPhaseListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void beforePhase(PhaseEvent arg0) {
		
		  Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().
				  getSessionMap().get(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);
		  if (e instanceof BadCredentialsException) {
			  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().
			  put(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY, null);
			  FacesUtils.addErrorMessage("Username or password not valid.");
			  }
	}

	public PhaseId getPhaseId() {
		 return PhaseId.RENDER_RESPONSE;
	}

}
