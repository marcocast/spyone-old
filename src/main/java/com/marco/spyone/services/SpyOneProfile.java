package com.marco.spyone.services;

import org.grep4j.core.model.Profile;
import org.grep4j.core.model.ProfileBuilder;

public enum SpyOneProfile {

	PMU(ProfileBuilder.newBuilder()
			.name("pmu")
			.filePath("/opt/ops/logs/jboss/phase-pmu-handler/server.log*")
			.onRemotehost("dubdc2-uat2jeeramppmuhandler-01.unix.paddypower.com")
			.credentials("lgajda", "8d2CkM47")
			.build()),
	PP(ProfileBuilder.newBuilder()
			.name("pp")
			.filePath("/opt/ops/logs/jboss/phase-pp-handler/server.log*")
			.onRemotehost("dubdc2-uat2jeeramppphandler-01.unix.paddypower.com")
			.credentials("lgajda", "8d2CkM47")
			.build()),
	SB(ProfileBuilder.newBuilder()
			.name("sb")
			.filePath("/opt/ops/logs/jboss/phase-sb-handler/server.log*")
			.onRemotehost("dubdc2-uat2jeerampsbhandler-01.unix.paddypower.com")
			.credentials("lgajda", "8d2CkM47")
			.build());

	private Profile profile;

	private SpyOneProfile(Profile profile) {
		this.profile = profile;
	}

	public Profile getProfile() {
		return profile;
	}

}
