package com.monito.profiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.grep4j.core.model.Profile;


public class DiskStoredProfiles implements ProfilesDao {

	private static final String PROFILE_EXTENSION = ".prof";

	Map<String, Profile> profiles = new HashMap<String, Profile>();
	
	public DiskStoredProfiles(){
		readAllProfilesFromDisk();
	}

	@Override
	public Collection<String> listProfiles() {
		return profiles.keySet();
	}

	@Override
	public Collection<Profile> getAllProfiles() {
		return profiles.values();
	}

	@Override
	public Collection<Profile> getProfiles(String... profileNames) {
		ArrayList<Profile> filteredProfiles = new ArrayList<Profile>();
		for (String name : profileNames){
			if (profiles.containsKey(name)){
				filteredProfiles.add(profiles.get(name));
			}
		}
		return filteredProfiles;
	}

	@Override
	public void storeProfiles(Profile... profiles) {
		for (Profile profile : profiles) {
	        try {
	        	SerializableProfile serializedProfile = new SerializableProfile(profile);
	        	FileOutputStream file = new FileOutputStream(profile.getName() + PROFILE_EXTENSION);
	        	ObjectOutputStream profileWriter = new ObjectOutputStream(file);
	        	profileWriter.writeObject(serializedProfile);
	        	profileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void refreshProfiles() {
		readAllProfilesFromDisk();
	}
	
	private Profile readProfileFromDisk(String name) {
        try {
        	FileInputStream file = new FileInputStream(name + PROFILE_EXTENSION);
        	ObjectInputStream profileReader = new ObjectInputStream(file);
        	SerializableProfile serializedProfile = (SerializableProfile) profileReader.readObject();
			profileReader.close();
			return serializedProfile.rebuildProfile();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void readAllProfilesFromDisk() {
		Collection<String> profilesDiskNames = findDiskProfilesNames();
		for (String profileDiskName : profilesDiskNames) {
			String profileName = profileDiskName.replace(PROFILE_EXTENSION, "");
			profiles.put(profileName, readProfileFromDisk(profileName));
		}
	}

	private Collection<String> findDiskProfilesNames() {
		File currentDir = new File(System.getProperty("user.dir"));
		String[] existingProfilesInDisk = currentDir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.matches(".*\\" + PROFILE_EXTENSION + "$");
			}});
		return Arrays.asList(existingProfilesInDisk);
	}

}
