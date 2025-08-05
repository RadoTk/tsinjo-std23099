CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       nom VARCHAR(100) NOT NULL,
                       prenom VARCHAR(100) NOT NULL,
                       email VARCHAR(150) NOT NULL
);

CREATE TABLE donors (
                        id BIGSERIAL PRIMARY KEY,
                        user_id BIGINT NOT NULL,
                        CONSTRAINT fk_donor_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE beneficiaries (
                               id BIGSERIAL PRIMARY KEY,
                               user_id BIGINT NOT NULL,
                               CONSTRAINT fk_beneficiary_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE payments (
                          id BIGSERIAL PRIMARY KEY,
                          ref VARCHAR(50) NOT NULL,
                          amount NUMERIC(15,2) NOT NULL,
                          date TIMESTAMP NOT NULL,
                          payment_method VARCHAR(50),
                          status VARCHAR(20)
);

CREATE TABLE donations (
                           id BIGSERIAL PRIMARY KEY,
                           donor_id BIGINT NOT NULL,
                           payment_id BIGINT NOT NULL,
                           CONSTRAINT fk_donation_donor FOREIGN KEY (donor_id) REFERENCES donors(id),
                           CONSTRAINT fk_donation_payment FOREIGN KEY (payment_id) REFERENCES payments(id)
);

CREATE TABLE helps (
                       id BIGSERIAL PRIMARY KEY,
                       beneficiary_id BIGINT NOT NULL,
                       payment_id BIGINT NOT NULL,
                       description TEXT,
                       CONSTRAINT fk_help_beneficiary FOREIGN KEY (beneficiary_id) REFERENCES beneficiaries(id),
                       CONSTRAINT fk_help_payment FOREIGN KEY (payment_id) REFERENCES payments(id)
);
