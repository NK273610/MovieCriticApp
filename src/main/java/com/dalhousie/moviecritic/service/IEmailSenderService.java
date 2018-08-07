package com.dalhousie.moviecritic.service;

import javax.mail.SendFailedException;


public interface IEmailSenderService {

	public void sendPasswordChangeEmail(String emailId, String newPassword) throws SendFailedException ;

}
