package com.mploed.aggregate.jmolecules;

public record Points(int points) {

	public Points plus(Points anderePunkte) {
		return new Points(points + anderePunkte.points);
	}
}
