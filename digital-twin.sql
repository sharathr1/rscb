CREATE SCHEMA digitaltwin;

DROP TABLE
IF EXISTS digitaltwin.modality_cust;

CREATE TABLE digitaltwin.modality_cust (
	id BIGINT,
	customer_name VARCHAR(255),
	ib_count int,
	modality VARCHAR(255),
	rnk int,
	PRIMARY KEY (id)
	);

DROP TABLE
IF EXISTS digitaltwin.modality_service;
	CREATE TABLE digitaltwin.modality_service (
		id BIGINT,
		age int,
		ib_count int,
		modality VARCHAR(255),
		region VARCHAR(255),
		system_coverage_level_warrantyc VARCHAR(255),
		PRIMARY KEY (id)
		);
		
		
INSERT INTO digitaltwin.modality_cust(
	id, customer_name, ib_count, modality, rnk)
	VALUES (1, 'Chang Gung Memorial Hospital-Lin Ko',22, 'IS',1);
	
INSERT INTO digitaltwin.modality_service(
	id, age, ib_count, modality, region, system_coverage_level_warrantyc)
	VALUES (1, 'P3',4, 'MR', 'USCAN', 'Warranty');
	
	
DROP TABLE
IF EXISTS digitaltwin.region_contract;

CREATE TABLE digitaltwin.region_contract
(
  id bigint NOT NULL,
  region character varying(64),
  priduct character varying(64),
  contract_name character varying(255),
  CONSTRAINT region_contract_pkey PRIMARY KEY (id)
)
---- Workspace sratch pad -----------
DROP TABLE
IF EXISTS digitaltwin.modality_service;
CREATE TABLE digitaltwin.modality_service
(
  modality character varying(255),
  region character varying(255),
  system_coverage_level_warrantyc character varying(255),
  ib_count integer,
  avg_age numeric
)
ALTER TABLE digitaltwin.modality_service ADD COLUMN id SERIAL PRIMARY KEY;
ALTER TABLE digitaltwin.modality_service ALTER COLUMN id type bigint;
ALTER TABLE digitaltwin.modality_service ALTER COLUMN avg_age type decimal using avg_age::decimal;;

SELECT column_name, data_type, character_maximum_length
FROM INFORMATION_SCHEMA.COLUMNS
WHERE table_schema = 'digitaltwin'
AND table_name   = 'modality_service'

CREATE TABLE digitaltwin.customer_ib
(	
  customer_name character varying(1024),
  ib_count integer
)
ALTER TABLE digitaltwin.customer_ib ADD COLUMN id SERIAL PRIMARY KEY;
ALTER TABLE digitaltwin.customer_ib ALTER COLUMN id type bigint;

systemid|componentsdesc

CREATE TABLE digitaltwin.system_component
(
  systemid character varying(256),
  componentsdesc character varying(1024)
)
ALTER TABLE digitaltwin.system_component ADD COLUMN id SERIAL PRIMARY KEY;
ALTER TABLE digitaltwin.system_component ALTER COLUMN id type bigint;

region_description|product_description|sr_process_year|sr_process_month|material_spend|labor_spend|avg_labor_hours|total_ge_cost

CREATE TABLE digitaltwin.region_expenditure
(
  region_description character varying(256),
  product_description character varying(1024),
  sr_process_year integer,
  sr_process_month integer,
  material_spend numeric,
  labor_spend numeric,
  avg_labor_hours numeric,
  total_ge_cost numeric
)
ALTER TABLE digitaltwin.region_expenditure ADD COLUMN id SERIAL PRIMARY KEY;
ALTER TABLE digitaltwin.region_expenditure ALTER COLUMN id type bigint;








	