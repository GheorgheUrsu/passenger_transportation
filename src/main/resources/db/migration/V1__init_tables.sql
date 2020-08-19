CREATE TABLE BUSSES (
        BUSS_ID             BIGINT PRIMARY KEY AUTO_INCREMENT,
        BUSS_TYPE           VARCHAR(20),
        LAST_INSPECTION     DATE,
        REG_NUMBER          VARCHAR(10)
    );

     CREATE TABLE PASSENGERS (
        PASSENGER_ID        BIGINT  PRIMARY KEY AUTO_INCREMENT,
        BIRTH_DATE          DATE NOT NULL ,
        LUGGAGE_WEIGHT      INTEGER,
        PASSENGER_NAME      VARCHAR(30) NOT NULL ,
        PASSPORT_DATA       VARCHAR(14) NOT NULL
    );

    CREATE TABLE ROUTES (
        ROUTE_ID            BIGINT  PRIMARY KEY AUTO_INCREMENT,
        ARRIVAL_DATE        DATETIME NOT NULL ,
        ARRIVAL_LOC         VARCHAR(30) NOT NULL ,
        START_DATE          DATETIME NOT NULL ,
        START_LOC           VARCHAR(30) NOT NULL ,
        BUSS_ID             BIGINT
    );

    CREATE TABLE TICKETS (
        TICKETS_ID          BIGINT PRIMARY KEY AUTO_INCREMENT,
        TICKET_PRICE        INTEGER,
        TICKET_TYPE         VARCHAR(20),
        PASSENGER_ID        BIGINT,
        ROUTE_ID            BIGINT
    );


     ALTER TABLE ROUTES
        ADD CONSTRAINT ROUTES_BUSS_FK FOREIGN KEY (BUSS_ID)
            REFERENCES BUSSES (BUSS_ID) ;

     ALTER TABLE TICKETS
        ADD CONSTRAINT TICKET_PASS_FK FOREIGN KEY (PASSENGER_ID)
            REFERENCES PASSENGERS (PASSENGER_ID);

     ALTER TABLE TICKETS
       ADD CONSTRAINT TICKET_ROUTE_FK FOREIGN KEY (ROUTE_ID)
            REFERENCES ROUTES (ROUTE_ID);