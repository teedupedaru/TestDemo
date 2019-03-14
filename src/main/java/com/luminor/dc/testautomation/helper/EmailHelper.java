package com.luminor.dc.testautomation.helper;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class EmailHelper {

	private Store store;

	public EmailHelper(String host, String port, String username, String password) {

		Properties properties = new Properties();
		properties.put("mail.pop3.host", host);
		properties.put("mail.pop3.port", port);
		properties.put("mail.pop3.starttls.enable", "true");

		Session emailSession = Session.getDefaultInstance(properties);

		// create the POP3 store object and connect with the pop server
		try {
			store = emailSession.getStore("pop3s");
			store.connect(host, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getLastEmailContent() {
		Message lastMessage;
		try {
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();
			lastMessage = messages[messages.length - 1];
			String lastMessageContent = lastMessage.getContent().toString();
			// close the store and folder objects
			emailFolder.close(false);
			store.close();
			return lastMessageContent;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getLastEmailContentByRegexp(String pattern) {
	      Pattern r = Pattern.compile(pattern);

	      Matcher m = r.matcher(getLastEmailContent());
	      return m.group();      
	}
}
