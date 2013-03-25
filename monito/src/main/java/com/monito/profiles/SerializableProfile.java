package com.monito.profiles;

import java.io.Serializable;

import org.grep4j.core.model.Profile;
import org.grep4j.core.model.ServerDetails;

public class SerializableProfile implements Serializable {

		private static final long serialVersionUID = -7957849274070172271L;
		
		private String name;
		private String filePath;
		private String host;
		private String user;
		private String password;
		private Integer port;
		private String privateKeyLocation;
		private boolean isPasswordRequired;

		public SerializableProfile(Profile profile) {
			name = profile.getName();
			filePath = profile.getFilePath();
			ServerDetails sd = profile.getServerDetails();
			host = sd.getHost();
			user = sd.getUser();
			password = sd.getPassword();
			port = sd.getPort();
			privateKeyLocation = sd.getPrivateKeyLocation();
			isPasswordRequired = sd.isPasswordRequired();
		}
		
		public Profile rebuildProfile(){
			Profile profile = new Profile(name, filePath);
			ServerDetails sd = new ServerDetails(host);
			sd.setUser(user);
			sd.setPassword(password);
			sd.setPort(port);
			sd.setPrivateKeyLocation(privateKeyLocation);
			sd.setPasswordRequired(isPasswordRequired);
			profile.setServerDetails(sd);
			return profile;
		}
}
