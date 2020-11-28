CREATE SCHEMA web3_project_r0748609; -- replace r123456 with your studentnr

GRANT ALL ON SCHEMA web3_project_r0748609 TO lector WITH GRANT OPTION; -- replace r123456 with your studentnr
GRANT ALL ON SCHEMA web3_project_r0748609 TO r0748609; -- replace r123456 with your studentnr
GRANT ALL ON SCHEMA web3_project_r0748609 TO local_r0748609; -- replace r123456 with your studentnr

SET search_path TO web3_project_r0748609; -- replace r123456 with your studentnr

DROP TABLE IF EXISTS web3_project_r0748609.user; -- replace r123456 with your studentnr

CREATE TABLE web3_project_r0748609.user ( -- replace r123456 with your studentnr
                                            userid character varying(50) PRIMARY KEY,
                                            firstname character varying(50) NOT NULL,
                                            lastname character varying(50) NOT NULL,
                                            email character varying(64) NOT NULL,
                                            password character (128) NOT NULL,
                                            role character varying(32) DEFAULT 'Customer' NULL
);

GRANT ALL ON TABLE web3_project_r0748609.user TO lector WITH GRANT OPTION; -- replace r123456 with your studentnr
GRANT ALL ON TABLE web3_project_r0748609.user TO r0748609; -- replace r123456 with your studentnr
GRANT ALL ON TABLE web3_project_r0748609.user TO local_r0748609; -- replace r123456 with your studentnr

DROP TABLE IF EXISTS web3_project_r0748609.contact; -- replace r123456 with your studentnr

CREATE TABLE web3_project_r0748609.contact ( -- replace r123456 with your studentnr
                                               id SERIAL PRIMARY KEY,
                                               user_id character varying(50) NULL,
                                               firstname character varying(50) NOT NULL,
                                               lastname character varying(50) NOT NULL,
                                               email character varying(64) NOT NULL,
                                               phonenumber character varying(15) NOT NULL,
                                               date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                               CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES "user"(userid)
);

GRANT ALL ON TABLE web3_project_r0748609.contact TO lector; -- replace r123456 with your studentnr
GRANT ALL ON TABLE web3_project_r0748609.contact TO r0748609; -- replace r123456 with your studentnr
GRANT ALL ON TABLE web3_project_r0748609.contact TO local_r0748609; -- replace r123456 with your studentnr

GRANT ALL ON SEQUENCE web3_project_r0748609.contact_id_seq TO lector WITH GRANT OPTION;
GRANT ALL ON SEQUENCE web3_project_r0748609.contact_id_seq TO local_r0748609;

CREATE TABLE web3_project_r0748609.positivetest (
                                                user_id character varying(50) NOT NULL,
                                                date date NOT NULL,
                                                CONSTRAINT fk_positivetest FOREIGN KEY (user_id) REFERENCES "user"(userid),
                                                CONSTRAINT pk_positivetest PRIMARY KEY(user_id,date)

);

GRANT ALL ON TABLE web3_project_r0748609.positivetest TO lector; -- replace r123456 with your studentnr
GRANT ALL ON TABLE web3_project_r0748609.positivetest TO r0748609; -- replace r123456 with your studentnr
GRANT ALL ON TABLE web3_project_r0748609.positivetest TO local_r0748609; -- replace r123456 with your studentnr

INSERT INTO web3_project_r0748609.user (userid, firstname, lastname, email, password, role) VALUES ('yenthe', 'Yenthe', 'Bruynseels', 'yenthe@thibault.islove', '2c909db24c974545f5b4956c88c2991e9df98e67dc39db6252c2d54ae9d01132463fe1939f7256abd619d98ba9a96870175c69c232c985a0d3523554ff813b0d', 'admin');
INSERT INTO web3_project_r0748609.user (userid, firstname, lastname, email, password, role) VALUES ('admin', 'Admini', 'Strator', 'admin@ucll.be', '99f97d455d5d62b24f3a942a1abc3fa8863fc0ce2037f52f09bd785b22b800d4f2e7b2b614cb600ffc2a4fe24679845b24886d69bb776fcfa46e54d188889c6f', 'admin');
INSERT INTO web3_project_r0748609.user (userid, firstname, lastname, email, password, role) VALUES ('thibault', 'Thibault', 'Magnini', 'thibault@single.forever', 'bac40d1a5a279a23f70eb37a5f0a1020ee1980f9a33369da5a1e46b0a71799bbb79d7a9b41e4d5da8bae2433f441b235051132e17359a8005d12c088dbf0423a', 'customer');