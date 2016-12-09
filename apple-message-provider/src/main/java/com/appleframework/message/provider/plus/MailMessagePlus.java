package com.appleframework.message.provider.plus;

import com.appleframework.message.provider.exception.MessageException;

public interface MailMessagePlus extends MessagePlus {
		
	public boolean doSend(String mailTo,String mailSubject, String mailBody,String ccMail,String bccMail,String isReadToMail) throws MessageException;

}
