package com.mploed.aggregate.jmolecules;

import org.jmolecules.ddd.types.ValueObject;

record City(String city) implements ValueObject {

	public Points calculatePoints() {

		if ("Hamburg".equalsIgnoreCase(city)) {
			return new Points(5);
		} else if ("München".equalsIgnoreCase(city)) {
			return new Points(5);
		} else {
			return new Points(0);
		}
	}
}
