-- Drop table

-- DROP TABLE public.users

CREATE TABLE public.users (
	user_id uuid NOT NULL,
	first_name varchar NOT NULL,
	last_name varchar NOT NULL,
	age int4 NULL
);
