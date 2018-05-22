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