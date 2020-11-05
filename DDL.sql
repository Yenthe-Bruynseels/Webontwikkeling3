CREATE SCHEMA web3_project_r0748609; -- replace r123456 with your studentnr

GRANT ALL ON SCHEMA web3_project_r0748609 TO lector WITH GRANT OPTION; -- replace r123456 with your studentnr
GRANT ALL ON SCHEMA web3_project_r0748609 TO r0748609; -- replace r123456 with your studentnr
GRANT ALL ON SCHEMA web3_project_r0748609 TO local_r0748609; -- replace r123456 with your studentnr

SET search_path TO web3_project_r0748609; -- replace r123456 with your studentnr

DROP TABLE IF EXISTS web3_project_r0748609.user; -- replace r123456 with your studentnr

CREATE TABLE web3_project_r0748609.user ( -- replace r123456 with your studentnr
                                            userid character varying(50) PRIMARY KEY,
                                            firstname character varying(32) NOT NULL,
                                            lastname character varying(32) NOT NULL,
                                            email character varying(64) NOT NULL,
                                            password character (128) NOT NULL
);

GRANT ALL ON TABLE web3_project_r0748609.user TO lector WITH GRANT OPTION; -- replace r123456 with your studentnr
GRANT ALL ON TABLE web3_project_r0748609.user TO r0748609; -- replace r123456 with your studentnr
GRANT ALL ON TABLE web3_project_r0748609.user TO local_r0748609; -- replace r123456 with your studentnr

DROP TABLE IF EXISTS web3_project_r0748609.contact; -- replace r123456 with your studentnr

CREATE TABLE web3_project_r0748609.contact ( -- replace r123456 with your studentnr
                                               id SERIAL PRIMARY KEY,
                                               person_id integer NULL,
                                               firstname character varying(32) NOT NULL,
                                               lastname character varying(32) NOT NULL,
                                               email character varying(64) NOT NULL,
                                               phonenumber character varying(15) NOT NULL,
                                               date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

GRANT ALL ON TABLE web3_project_r0748609.contact TO lector; -- replace r123456 with your studentnr
GRANT ALL ON TABLE web3_project_r0748609.contact TO r0748609; -- replace r123456 with your studentnr
GRANT ALL ON TABLE web3_project_r0748609.contact TO local_r0748609; -- replace r123456 with your studentnr

GRANT ALL ON SEQUENCE web3_project_r0748609.contact_id_seq TO lector WITH GRANT OPTION;
GRANT ALL ON SEQUENCE web3_project_r0748609.contact_id_seq TO local_r0748609;