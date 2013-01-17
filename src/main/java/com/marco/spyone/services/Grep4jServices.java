package com.marco.spyone.services;

import static org.grep4j.core.Grep4j.constantExpression;
import static org.grep4j.core.Grep4j.grep;
import static org.grep4j.core.options.Option.maxMatches;
import static org.grep4j.core.fluent.Dictionary.on;

import java.util.List;

import org.grep4j.core.model.Profile;
import org.grep4j.core.result.GrepResults;

public class Grep4jServices {

	public GrepResults keywordSearch(String keyword, List<Profile> profiles) {

		return grep(constantExpression(keyword), on(profiles));
	}

}
