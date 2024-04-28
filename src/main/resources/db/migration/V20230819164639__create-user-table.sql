-- public.tbl_users definition

-- Drop table

DROP TABLE public.tbl_users;

CREATE TABLE public.tbl_users (
	user_id bytea NOT NULL,
	email varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	profile_image_upload_id bytea NULL,
	last_name varchar(255) NULL,
	city varchar NULL,
	country varchar NULL,
	postal_code varchar NULL,
	about_me varchar NULL,
	created_at timestamp NULL,
	updated_at timestamp NULL,
	CONSTRAINT tbl_users_pkey PRIMARY KEY (user_id)
);
