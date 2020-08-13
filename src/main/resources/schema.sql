 create table routes (
        route_id            bigint              not null auto_increment primary key,
        arrival_date        datetime,
        arrival_loc         varchar(255),
        buss_type           varchar(255),
        last_inspection     datetime,
        reg_number          varchar(255),
        start_date          datetime,
        start_loc           varchar(255),
    )

    create table tickets (
       tickets_id           bigint               not null auto_increment primary KEY,
        birth_date          datetime,
        luggage_weight      integer not null,
        name                varchar(255),
        passport_data       varchar(255),
        ticket_price        integer,
        type                varchar(255),
        route_id            bigint,
    )

    alter table tickets
                 add constraint FK_ROUTE_ID
                             foreign key (route_id)
                                                references routes (route_id)