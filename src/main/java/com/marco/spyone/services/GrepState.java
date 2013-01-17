package com.marco.spyone.services;

import org.grep4j.core.result.GrepResults;

public class GrepState {

	private GrepResults state;

	public GrepResults getState() {
		return state;
	}

	public void setState(GrepResults state) {
		this.state = state;
	}

	@Override
	public String toString() {
		if (state != null) {
			return state.toString();
		}
		return "null";
	}
}
