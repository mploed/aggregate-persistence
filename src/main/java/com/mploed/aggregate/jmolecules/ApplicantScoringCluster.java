package com.mploed.aggregate.jmolecules;

import java.math.BigDecimal;
import java.util.UUID;

import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;

import com.mploed.aggregate.jmolecules.ApplicantScoringCluster.ClusterIdentifier;

/**
 * @author Oliver Drotbohm
 */
public class ApplicantScoringCluster implements AggregateRoot<ApplicantScoringCluster, ClusterIdentifier> {

	public record ClusterIdentifier(UUID id) implements Identifier {}

	private final ClusterIdentifier id;
	private final ApplicationNumber applicationNumber;
	private City city;
	private BalanceAtBank balance;

	public ApplicantScoringCluster(ApplicationNumber applicationNumber) {

		if (applicationNumber == null) {
			throw new IllegalArgumentException("Antragsnummer darf nicht null sein.");
		}

		this.id = new ClusterIdentifier(UUID.randomUUID());
		this.applicationNumber = applicationNumber;
		this.balance = new BalanceAtBank(0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jmolecules.ddd.types.Identifiable#getId()
	 */
	@Override
	public ClusterIdentifier getId() {
		return id;
	}

	public Points score() {

		if (city == null) {
			return null;
		}

		if (balance == null) {
			return null;
		}

		return new Points(0)
				.plus(city.calculatePoints())
				.plus(balance.calculatePoints());
	}

	public ApplicationNumber applicationNumber() {
		return applicationNumber;
	}

	public ApplicantScoringCluster addCity(String wohnort) {

		this.city = new City(wohnort);

		return this;
	}

	public ApplicantScoringCluster addBalance(BigDecimal balance) {

		this.balance = new BalanceAtBank(balance);

		return this;
	}
}
