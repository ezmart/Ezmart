-- Drop table

-- DROP TABLE public.avaliation

CREATE TABLE public.avaliation (
	avaliation_id serial NOT NULL,
	avaliation_satisfaction int4 NOT NULL,
	avaliation_productprice int4 NOT NULL,
	avaliation_diversity int4 NOT NULL,
	avaliation_employees int4 NOT NULL,
	avaliation_ambience int4 NOT NULL,
	avaliation_commentary varchar(1000) NULL,
	avaliation_consumerid int4 NOT NULL,
	avaliation_establishmentid int4 NOT NULL,
	avaliation_date date NOT NULL,
	CONSTRAINT pk_avaliation PRIMARY KEY (avaliation_id),
	CONSTRAINT fk1_avaliation FOREIGN KEY (avaliation_consumerid) REFERENCES consumer(consumer_id) ON UPDATE CASCADE,
	CONSTRAINT fk2_avaliation FOREIGN KEY (avaliation_establishmentid) REFERENCES establishment(establishment_id) ON UPDATE CASCADE
)
WITH (
	OIDS=FALSE
) ;

-- Drop table

-- DROP TABLE public.city

CREATE TABLE public.city (
	city_id serial NOT NULL,
	city_name text NOT NULL,
	city_codeibge text NOT NULL,
	city_stateid int4 NOT NULL,
	CONSTRAINT city_city_codeibge_key UNIQUE (city_codeibge),
	CONSTRAINT pk_city PRIMARY KEY (city_id),
	CONSTRAINT fk1_city FOREIGN KEY (city_stateid) REFERENCES state(state_id) ON UPDATE CASCADE
)
WITH (
	OIDS=FALSE
) ;

-- Drop table

-- DROP TABLE public.consumer

CREATE TABLE public.consumer (
	consumer_id serial NOT NULL,
	consumer_name varchar(250) NOT NULL,
	consumer_lastname varchar(100) NOT NULL,
	consumer_cpf varchar(11) NOT NULL,
	consumer_userid int4 NOT NULL,
	CONSTRAINT consumer_consumer_cpf_key UNIQUE (consumer_cpf),
	CONSTRAINT pk_consumer PRIMARY KEY (consumer_id)
)
WITH (
	OIDS=FALSE
) ;

-- Drop table

-- DROP TABLE public.establishment

CREATE TABLE public.establishment (
	establishment_id serial NOT NULL,
	establishment_cnpj varchar(18) NOT NULL,
	establishment_secondemail text NOT NULL,
	establishment_name varchar(100) NULL,
	establishment_businessname varchar(100) NOT NULL,
	establishment_plan int4 NULL,
	establishment_planstartdate date NULL,
	establishment_planfinaldate date NULL,
	establishment_userid int4 NOT NULL,
	CONSTRAINT establishment_establishment_cnpj_key UNIQUE (establishment_cnpj),
	CONSTRAINT pk_establishment PRIMARY KEY (establishment_id)
)
WITH (
	OIDS=FALSE
) ;

-- Drop table

-- DROP TABLE public.establishmentproduct

CREATE TABLE public.establishmentproduct (
	establishmentproduct_id serial NOT NULL,
	establishmentproduct_establishmentid int4 NOT NULL,
	establishmentproduct_productid int4 NOT NULL,
	establishmentproduct_price numeric NOT NULL,
	establishmentproduct_date timestamp NULL,
	CONSTRAINT pk_establishmentproduct PRIMARY KEY (establishmentproduct_id),
	CONSTRAINT fk1_establishmentproduct FOREIGN KEY (establishmentproduct_establishmentid) REFERENCES establishment(establishment_id) ON UPDATE CASCADE,
	CONSTRAINT fk2_establishmentproduct FOREIGN KEY (establishmentproduct_productid) REFERENCES product(product_id) ON UPDATE CASCADE
)
WITH (
	OIDS=FALSE
) ;

-- Drop table

-- DROP TABLE public.promotion

CREATE TABLE public.promotion (
	promotion_id serial NOT NULL,
	promotion_name varchar(50) NULL
	CONSTRAINT pk_promotion PRIMARY KEY (promotion_id)
)
WITH (
	OIDS=FALSE
) ;

CREATE TABLE public.promotionestablishmentproduct (
	promotionestablishmentproduct_id serial NOT NULL,
	promotionestablishmentproduct_promotionid int4 NOT NULL,
	promotionestablishmentproduct_establishmentproductid int4 NOT NULL,
	promotionestablishmentproduct_startdate date NOT NULL,
	promotionestablishmentproduct_finaldate date NOT NULL,
	CONSTRAINT pk_promotionestablishmentproduct PRIMARY KEY (promotionestablishmentproduct_id),
	CONSTRAINT fk1_promotionestablishmentproduct FOREIGN KEY (promotionestablishmentproduct_promotionid) REFERENCES promotion(promotion_id) ON UPDATE CASCADE,
	CONSTRAINT fk2_promotionestablishmentproduct FOREIGN KEY (promotionestablishmentproduct_establishmentproductid) REFERENCES establishmentproduct(establishmentproduct_id) ON UPDATE CASCADE
)
WITH (
	OIDS=FALSE
) ;

-- Drop table

-- DROP TABLE public.list

CREATE TABLE public.list (
	list_id serial NOT NULL,
	list_consumerid int4 NOT NULL,
	list_favorite bool NULL DEFAULT false,
	list_name varchar(50) NULL DEFAULT 'Sem nome'::character varying,
	list_date date NOT NULL,
	CONSTRAINT pk_list PRIMARY KEY (list_id),
	CONSTRAINT fk1_list FOREIGN KEY (list_consumerid) REFERENCES consumer(consumer_id) ON UPDATE CASCADE
)
WITH (
	OIDS=FALSE
) ;

-- Drop table

-- DROP TABLE public.listproduct

CREATE TABLE public.listproduct (
	listproduct_id serial NOT NULL,
	listproduct_listid int4 NOT NULL,
	listproduct_productid int4 NOT NULL,
	listproduct_quantity int4 NOT NULL,
	CONSTRAINT pk_listproduct PRIMARY KEY (listproduct_id),
	CONSTRAINT fk1_listproduct FOREIGN KEY (listproduct_listid) REFERENCES list(list_id) ON UPDATE CASCADE,
	CONSTRAINT fk2_listproduct FOREIGN KEY (listproduct_productid) REFERENCES product(product_id) ON UPDATE CASCADE
)
WITH (
	OIDS=FALSE
) ;

-- Drop table

-- DROP TABLE public.product

CREATE TABLE public.product (
	product_id serial NOT NULL,
	product_sectorid int4 NOT NULL,
	product_providerid int4 NOT NULL,
	product_barcode varchar(15) NOT NULL,
	product_name varchar(150) NOT NULL,
	product_image bytea NULL,
	product_brand varchar(100) NULL,
	CONSTRAINT pk_product PRIMARY KEY (product_id),
	CONSTRAINT fk1_product FOREIGN KEY (product_sectorid) REFERENCES sector(sector_id) ON UPDATE CASCADE,
	CONSTRAINT fk2_product FOREIGN KEY (product_providerid) REFERENCES provider(provider_id) ON UPDATE CASCADE
)
WITH (
	OIDS=FALSE
) ;

-- Drop table

-- DROP TABLE public.provider

CREATE TABLE public.provider (
	provider_id serial NOT NULL,
	provider_cnpj varchar(20) NOT NULL,
	provider_name varchar(100) NULL,
	provider_businessname varchar(100) NOT NULL,
	CONSTRAINT pk_provider PRIMARY KEY (provider_id),
	CONSTRAINT provider_provider_cnpj_key UNIQUE (provider_cnpj)
)
WITH (
	OIDS=FALSE
) ;

-- Drop table

-- DROP TABLE public.sector

CREATE TABLE public.sector (
	sector_id serial NOT NULL,
	sector_name varchar(100) NOT NULL,
	CONSTRAINT pk_sector PRIMARY KEY (sector_id)
)
WITH (
	OIDS=FALSE
) ;

-- Drop table

-- DROP TABLE public.state

CREATE TABLE public.state (
	state_id serial NOT NULL,
	state_name text NOT NULL,
	state_initials varchar(2) NOT NULL,
	CONSTRAINT pk_state PRIMARY KEY (state_id),
	CONSTRAINT state_state_initials_key UNIQUE (state_initials),
	CONSTRAINT state_state_name_key UNIQUE (state_name)
)
WITH (
	OIDS=FALSE
) ;

-- Drop table

-- DROP TABLE public.usersystem

CREATE TABLE public.usersystem (
	usersystem_id serial NOT NULL,
	usersystem_email varchar(100) NOT NULL,
	usersystem_password varchar(100) NOT NULL,
	usersystem_addresslocation varchar(100) NOT NULL,
	usersystem_numberhouse varchar(10) NOT NULL,
	usersystem_neighborhood varchar(100) NOT NULL,
	usersystem_cityid int4 NOT NULL,
	usersystem_zipcode varchar(100) NOT NULL,
	usersystem_telephone varchar(100) NOT NULL,
	usersystem_usertype varchar(100) NOT NULL,
	usersystem_active bool NOT NULL,
	usersystem_latitude text NULL,
	usersystem_longitude text NULL,
	usersystem_img bytea NULL,
	CONSTRAINT usersystem_usersystem_email_key UNIQUE (usersystem_email)
)
WITH (
	OIDS=FALSE
) ;
