CREATE TABLE scoring_applicant_cluster (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    application_number VARCHAR(255) NOT NULL,
    city VARCHAR(255),
    balance_at_bank DECIMAL(19, 2),
    version BIGINT
);
CREATE UNIQUE INDEX idx_scoring_antragsteller_antragsnummer ON scoring_applicant_cluster(application_number);

CREATE TABLE applicant_scoring_events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    application_number VARCHAR(255) NOT NULL,
    event_type VARCHAR(255) NOT NULL,
    event_data CLOB NOT NULL,
    version BIGINT NOT NULL,
    occurred_on TIMESTAMP NOT NULL,
    UNIQUE(application_number, version)
);

CREATE INDEX idx_applicant_scoring_events_app_number ON applicant_scoring_events(application_number);
CREATE INDEX idx_applicant_scoring_events_app_version ON applicant_scoring_events(application_number, version);
CREATE INDEX idx_applicant_scoring_events_occurred_on ON applicant_scoring_events(occurred_on);
