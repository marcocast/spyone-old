package com.monito.profiles;

import java.util.Collection;

import org.grep4j.core.model.Profile;


public interface ProfilesDao {

	public Collection<String> listProfiles();
	
	public Collection<Profile> getAllProfiles();
	
	public Collection<Profile> getProfiles(String... profileNames);
	
	public void storeProfiles(Profile... profiles);
	
	public void refreshProfiles();

}
