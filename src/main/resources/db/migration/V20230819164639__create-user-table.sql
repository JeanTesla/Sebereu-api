-- public.tbl_users definition

-- Drop table

DROP TABLE IF EXISTS public.tbl_users;

CREATE TABLE public.tbl_users (
	user_id bytea NOT NULL,
	user_name varchar(20) NOT NULL,
	first_name varchar NOT NULL,
	last_name varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	city varchar NULL,
	state bpchar(2) NULL,
	country varchar NULL,
	postal_code varchar NULL,
	address varchar NULL,
	about_me varchar(500) NULL,
	quick_message varchar(255) NULL,
	profile_image_upload_id bytea NULL,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT NULL,
	CONSTRAINT tbl_users_pkey PRIMARY KEY (user_id)
);
