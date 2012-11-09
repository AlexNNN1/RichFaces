package org.mypack.controllers;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mypack.helpers.AuthenticationService;

@SuppressWarnings("serial")
@ManagedBean(name = "loginController")
@RequestScoped
public class LoginController implements Serializable {
	private String userName;
	private String password;

	@ManagedProperty(value = "#{authenticationService}")
	private AuthenticationService authenticationService;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	protected final Log logger = LogFactory.getLog(getClass());

	public String login() throws ServletException, IOException {
		System.out.println("Пользователь: " + userName);
		System.out.println("Пароль: " + password);
		boolean success = authenticationService.login(userName, password);
		if (success) {
			return "home.xhtml"; // return to application but being logged now
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Login or password incorrect."));
			return "login.xhtml";
		}
	}
}
