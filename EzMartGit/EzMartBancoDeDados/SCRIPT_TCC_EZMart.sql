-- Ez Mart
-- Author:
   -- Marcos H
---------------------------------------------------------------------------
--Tabela Estado
create table state(
	state_id serial,
	state_name text unique not null,
	state_initials character varying(2) unique not null
);

---------------------------------------------------------------------------
--Tabela Município
create table city(
	city_id serial,
	city_name text not null,
	city_codeibge text unique not null,
	city_stateid int not null
);

---------------------------------------------------------------------------
--Tabela Usuário
create table usersystem(
	usersystem_id serial,
	usersystem_email character varying(100) unique not null,
	usersystem_password character varying(100) not null,
	usersystem_addresslocation character varying(100) not null,
	usersystem_numberhouse character varying(10) not null,
	usersystem_neighborhood character varying(100) not null,
	usersystem_cityid int not null,
	usersystem_zipcode character varying(100) not null,
	usersystem_telephone character varying(100) not null,
	usersystem_usertype character varying(100) not null,
	usersystem_active boolean not null,
	usersystem_latitude text,
	usersystem_longitude text,
	usersystem_img bytea
);

---------------------------------------------------------------------------
--Tabela Consumidor
create table consumer(
	consumer_id serial,
	consumer_name character varying(250) not null,
	consumer_lastname character varying(100) not null,
	consumer_cpf character varying(11) unique not null,
	consumer_userid int not null
);

---------------------------------------------------------------------------
--Tabela Estabelecimento
create table establishment(
	establishment_id serial,
	establishment_cnpj character varying(18) unique not null,
	establishment_secondemail text not null,
	establishment_name character varying(100),
	establishment_businessname character varying(100) not null,
	establishment_plan int,
	establishment_planstartdate date,
	establishment_planfinaldate date,
	establishment_userid int not null
);

---------------------------------------------------------------------------
--Tabela Avaliação
create table avaliation(
	avaliation_id serial,
	avaliation_satisfaction int not null,
	avaliation_productprice int not null,
	avaliation_diversity int not null,
	avaliation_employees int not null,
	avaliation_ambience int not null,
	avaliation_commentary character varying(1000),
	avaliation_consumerid int not null,
	avaliation_establishmentid int not null,
	avaliation_date date not null
);

---------------------------------------------------------------------------
-- Tabela Lista
create table list(
	list_id serial,
	list_consumerid int not null,
	list_favorite boolean default false,
	list_name character varying(50) default 'Sem nome',
	list_date date not null
);

---------------------------------------------------------------------------
--Tabela Produto
create table product(
	product_id serial,
	product_sectorid int not null,
	product_providerid int not null,
	product_barcode character varying(15) not null,
	product_name character varying(150) not null,
	product_image text
);

---------------------------------------------------------------------------
--Tabela Linha
create table sector(
	sector_id serial,
	sector_name character varying(100) not null
);	

---------------------------------------------------------------------------
--Tabela Fornecedor
create table provider(
	provider_id serial,
	provider_cnpj character varying(20) unique not null,
	provider_name character varying(100),
	provider_businessname character varying(100) not null
);

---------------------------------------------------------------------------
--Tabela Lista_Produto
create table listproduct(
	listproduct_id serial,
	listproduct_listid int not null,
	listproduct_productid int not null,
	listproduct_quantity int not null
);

---------------------------------------------------------------------------
--Tabela Promoção
create table promotion(
	promotion_id serial,
	promotion_name character varying(50),
	promotion_startdate date not null,
	promotion_finaldate date not null
);

---------------------------------------------------------------------------
--Tabela Empresa_Produto
create table establishmentproduct(
	establishmentproduct_id serial,
	establishmentproduct_establishmentid int not null,
	establishmentproduct_productid int not null,
	establishmentproduct_price numeric not null,
	establishmentproduct_promotionid int
);

---------------------------------------------------------------------------
alter table state add constraint pk_state primary key (state_id);

alter table city add constraint pk_city primary key (city_id);

alter table city add constraint fk1_city foreign key (city_stateid) references state(state_id)
	on update cascade on delete no action;

alter table usersystem add constraint pk_usersystem primary key (usersystem_id);

alter table usersystem add constraint fk1_usersystem foreign key (usersystem_cityid) references city(city_id)
	on update cascade on delete no action;

alter table consumer add constraint pk_consumer primary key (consumer_id);

alter table consumer add constraint fk1_consumer foreign key (consumer_userid) references usersystem(usersystem_id)
	on update cascade on delete no action;

alter table establishment add constraint pk_establishment primary key (establishment_id);

alter table establishment add constraint fk1_establishment foreign key (establishment_userid) references usersystem(usersystem_id)
	on update cascade on delete no action;

alter table avaliation add constraint pk_avaliation primary key(avaliation_id);

alter table avaliation add constraint fk1_avaliation foreign key(avaliation_consumerid)	references consumer(consumer_id) 
	on update cascade on delete no action;

alter table avaliation add constraint fk2_avaliation foreign key(avaliation_establishmentid) references establishment(establishment_id)
	 on update cascade on delete no action;

alter table list add constraint pk_list primary key (list_id);

alter table list add constraint fk1_list foreign key (list_consumerid) references consumer(consumer_id)
	on update cascade on delete no action;

alter table sector add constraint pk_sector primary key (sector_id);

alter table provider add constraint pk_provider primary key (provider_id);

alter table product add constraint pk_product primary key (product_id);

alter table product add constraint fk1_product foreign key (product_sectorid) references sector(sector_id)
	on update cascade on delete no action;

alter table product add constraint fk2_product foreign key (product_providerid) references provider(provider_id)
	on update cascade on delete no action;

alter table listproduct add constraint pk_listproduct primary key (listproduct_id);

alter table listproduct add constraint fk1_listproduct foreign key (listproduct_listid) references list(list_id)
	on update cascade on delete no action;

alter table listproduct add constraint fk2_listproduct foreign key (listproduct_productid) references product(product_id)
	on update cascade on delete no action;

alter table promotion add constraint pk_promotion primary key (promotion_id);

alter table establishmentproduct add constraint pk_establishmentproduct primary key (establishmentproduct_id);

alter table establishmentproduct add constraint fk1_establishmentproduct foreign key (establishmentproduct_establishmentid) references establishment(establishment_id)
	on update cascade on delete no action;

alter table establishmentproduct add constraint fk2_establishmentproduct foreign key (establishmentproduct_productid) references product(product_id)
	on update cascade on delete no action;

alter table establishmentproduct add constraint fk3_establishmentproduct foreign key (establishmentproduct_promotionid) references promotion(promotion_id)
	on update cascade on delete no action;