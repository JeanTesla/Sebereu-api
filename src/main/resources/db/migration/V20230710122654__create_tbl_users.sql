CREATE TABLE public.tbl_users (
	user_id bigserial NOT NULL,
	email varchar(255) NULL,
	"password" varchar(255) NULL,
	CONSTRAINT tbl_users_pkey PRIMARY KEY (user_id)
);