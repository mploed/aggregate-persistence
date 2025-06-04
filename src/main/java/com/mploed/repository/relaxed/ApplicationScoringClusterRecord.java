package com.mploed.repository.relaxed;

import com.mploed.aggregate.memento.ApplicantScoringClusterMemento;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("SCORING_APPLICANT_CLUSTER")
public class ApplicationScoringClusterRecord {
    @Id
    private Long id;

    private String applicationNumber;

    private String city;

    private BigDecimal balanceAtBank;

    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getBalanceAtBank() {
        return balanceAtBank;
    }

    public void setBalanceAtBank(BigDecimal balanceAtBank) {
        this.balanceAtBank = balanceAtBank;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
