package com.mploed.aggregate.jmolecules;

import org.jmolecules.ddd.types.ValueObject;

public record ApplicationNumber(String number) implements ValueObject {

	public ApplicationNumber {

		if (number == null) {
			throw new IllegalArgumentException("Antragsnummer darf nicht null sein.");
		}
	}
}
