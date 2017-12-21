-- Table: public.users

-- DROP TABLE public.users;
CREATE TABLE public.users
(
  id                    BIGINT NOT NULL DEFAULT nextval('seq_users' :: REGCLASS),
  email                 CHARACTER VARYING,
  password_hash         CHARACTER VARYING,
  rating                CHARACTER VARYING,
  password_hash_confirm CHARACTER VARYING,
  CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
  CONSTRAINT users_id_key UNIQUE (id)
)

-- Table: public.role

-- DROP TABLE public.role;

CREATE TABLE public.role
(
  id   BIGINT NOT NULL DEFAULT nextval('seq_role' :: REGCLASS),
  name CHARACTER VARYING,
  CONSTRAINT role_pkey PRIMARY KEY (id)
)

-- Table: public.users_role

-- DROP TABLE public.users_role;

CREATE TABLE public.users_role
(
  role_id BIGINT,
  user_id BIGINT,
  id      BIGINT NOT NULL DEFAULT nextval('users_role_id_seq' :: REGCLASS),
  CONSTRAINT fk_cdpd2ix59qroxmqubyjqplxn1 FOREIGN KEY (role_id)
  REFERENCES public.role (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT users_id_fkey FOREIGN KEY (user_id)
  REFERENCES public.users (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT users_role_role_id_fkey FOREIGN KEY (role_id)
  REFERENCES public.role (id) MATCH SIMPLE
  ON UPDATE CASCADE ON DELETE CASCADE
)

-- Table: public.categories

-- DROP TABLE public.categories;

CREATE TABLE public.categories
(
  id          BIGINT NOT NULL,
  name        CHARACTER VARYING,
  category_id BIGINT,
  CONSTRAINT subcategory_id_pkey PRIMARY KEY (id),
  CONSTRAINT category_id_fkey FOREIGN KEY (category_id)
  REFERENCES public.categories (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_dglte9qeu8l5fhggto4loyegg FOREIGN KEY (category_id)
  REFERENCES public.categories (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
-- Table: public.lot

-- DROP TABLE public.lot;

CREATE TABLE public.lot
(
  id           BIGINT NOT NULL DEFAULT nextval('lot_id_seq' :: REGCLASS),
  name         CHARACTER VARYING,
  user_id      BIGINT,
  description  CHARACTER VARYING,
  category_id  BIGINT,
  bayout_price DOUBLE PRECISION,
  photo        CHARACTER VARYING,
  min_price    DOUBLE PRECISION,
  max_price    DOUBLE PRECISION,
  CONSTRAINT lot_id_pkey PRIMARY KEY (id),
  CONSTRAINT fk_ckidvcwh811u6rbo27xdph6wm FOREIGN KEY (category_id)
  REFERENCES public.categories (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT lot_category_id_fkey FOREIGN KEY (category_id)
  REFERENCES public.categories (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT lot_user_id_fkey FOREIGN KEY (user_id)
  REFERENCES public.users (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
-- Table: public.rate

-- DROP TABLE public.rate;

CREATE TABLE public.rate
(
  id      BIGINT           NOT NULL,
  price   DOUBLE PRECISION NOT NULL,
  user_id BIGINT,
  lot_id  BIGINT,
  date    DATE,
  CONSTRAINT rate_id_pkey PRIMARY KEY (id),
  CONSTRAINT fk_199ephijfcrfwjywxdk1e5w0h FOREIGN KEY (lot_id)
  REFERENCES public.lot (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT rate_lot_id_fkey FOREIGN KEY (lot_id)
  REFERENCES public.lot (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT rate_user_id_fkey FOREIGN KEY (user_id)
  REFERENCES public.users (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)

-- Table: public.feature

-- DROP TABLE public.feature;

CREATE TABLE public.feature
(
  id          BIGINT NOT NULL,
  name        CHARACTER VARYING,
  lot_id      BIGINT,
  description CHARACTER VARYING,
  CONSTRAINT feature_id_pkey PRIMARY KEY (id),
  CONSTRAINT feature_lot_id_fkey FOREIGN KEY (lot_id)
  REFERENCES public.lot (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_qv33njtqir7jwogb13v6jx2wa FOREIGN KEY (lot_id)
  REFERENCES public.lot (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
-- Table: public.subscription

-- DROP TABLE public.subscription;

CREATE TABLE public.subscription
(
  id         BIGINT NOT NULL,
  creator_id BIGINT,
  user_id    BIGINT,
  date       DATE,
  CONSTRAINT subscription_id_pkey PRIMARY KEY (id),
  CONSTRAINT creator_id_fkey FOREIGN KEY (creator_id)
  REFERENCES public.users (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_id_fkey FOREIGN KEY (user_id)
  REFERENCES public.users (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)


--- sequence
CREATE SEQUENCE hibernate_sequence START 1;
CREATE SEQUENCE my_seq_gen START 1;


CREATE SEQUENCE public.seq_users
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 66
CACHE 1
CYCLE;

CREATE SEQUENCE public.users_role_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 4441
CACHE 1;

CREATE SEQUENCE public.seq_role
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 7
CACHE 1
CYCLE;

CREATE SEQUENCE public.lot_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 28
CACHE 1;