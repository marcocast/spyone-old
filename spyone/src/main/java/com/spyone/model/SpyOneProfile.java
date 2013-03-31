package com.spyone.model;

import javafx.beans.property.SimpleStringProperty;

@SuppressWarnings("restriction")
public class SpyOneProfile {
	   private final SimpleStringProperty profileName = new SimpleStringProperty("");
	   private final SimpleStringProperty filePath = new SimpleStringProperty("");
	   private final SimpleStringProperty host = new SimpleStringProperty("");
	   private final SimpleStringProperty user = new SimpleStringProperty("");
	   private final SimpleStringProperty password = new SimpleStringProperty("");

	public SpyOneProfile() {
	        this("", "", "","","");
	    }
	 
	    public SpyOneProfile(String pName, String filePath, String host, String user, String password) {
	    	setProfileName(pName);
	    	setFilePath(pName);
	    	setHost(pName);
	    	setUser(pName);
	    	setPassword(pName);
	        
	    }

	    public String getProfileName() {
	        return profileName.get();
	    }
	 
	    public void setProfileName(String pName) {
	    	profileName.set(pName);
	    }
	        
	    public String getFilePath() {
	        return filePath.get();
	    }
	    
	    public void setFilePath(String fpath) {
	    	filePath.set(fpath);
	    }
	    
	    public String getHost() {
	        return host.get();
	    }
	    
	    public void setHost(String fHost) {
	    	host.set(fHost);
	    }
	    
	    public String getUser() {
	        return user.get();
	    }
	    
	    public void setUser(String fUser) {
	    	user.set(fUser);
	    }
	    
	    public String getPassword() {
	        return password.get();
	    }
	    
	    public void setPassword(String fPassword) {
	    	password.set(fPassword);
	    }
	   
	}