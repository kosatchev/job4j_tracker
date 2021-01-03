CREATE sequence items_id_seq;
CREATE TABLE items (
	id int4 NOT NULL DEFAULT nextval('items_id_seq'),
	"name" text NOT NULL,
	description text NOT NULL,
	createdate timestamp DEFAULT now(),
	user_id int4 NOT NULL,
	category_id int4 NOT NULL,
	req_state_id int4 NOT NULL,
	CONSTRAINT item_pk PRIMARY KEY (id)
);