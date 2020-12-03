--Правила
CREATE sequence rules_id_seq;
CREATE TABLE public.rules (
	id int4 NOT NULL DEFAULT nextval('rules_id_seq'),
	"name" varchar NOT NULL,
	description text NOT NULL,
	CONSTRAINT rules_pk PRIMARY KEY (id),
	CONSTRAINT rules_un UNIQUE ("name")
);
COMMENT ON TABLE public.rules IS 'Правило роли';

--Роли
CREATE sequence roles_id_seq;
CREATE TABLE public.roles (
	id int4 NOT NULL DEFAULT nextval('roles_id_seq'),
	"name" varchar NOT NULL,
	description text NOT NULL,
	CONSTRAINT roles_pk PRIMARY KEY (id),
	CONSTRAINT roles_un UNIQUE ("name")
);
COMMENT ON TABLE public."roles" IS 'Роль в системе';

--Таблица связей ролей ип правил
CREATE sequence rolesrules_id_seq;
CREATE TABLE public.rolesrules (
	id int4 NOT NULL DEFAULT nextval('rolesrules_id_seq'),
	role_id int4 NOT NULL,
	rule_id int4 NOT NULL,
	CONSTRAINT rolesrules_pk PRIMARY KEY (id),
	CONSTRAINT rolesrules_fk_roles_id FOREIGN KEY (role_id) REFERENCES roles(id),
	CONSTRAINT rolesrules_fk_rules_id FOREIGN KEY (rule_id) REFERENCES rules(id),
	CONSTRAINT rolesrules_un UNIQUE (role_id, rule_id)
);
COMMENT ON TABLE public.rolesrules IS 'Правила ролей';

--Пользователи
CREATE sequence users_id_seq;
CREATE TABLE public.users (
	id int4 NOT NULL DEFAULT nextval('users_id_seq'),
	username varchar NOT NULL,
	role_id int4 NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id),
	CONSTRAINT users_fk_roles_id FOREIGN KEY (role_id) REFERENCES roles(id),
	CONSTRAINT users_un UNIQUE (username)
);
COMMENT ON TABLE public.users IS 'Пользователи';
COMMENT ON COLUMN public.users.username IS 'Имя пользователя';

--Категория заявки
CREATE sequence category_id_seq;
CREATE TABLE public.category (
	id int4 NOT NULL DEFAULT nextval('category_id_seq'),
	description text NOT NULL,
	CONSTRAINT category_pk PRIMARY KEY (id),
	CONSTRAINT category_un UNIQUE (description)
);
COMMENT ON TABLE public.category IS 'Категория заявки';

--Состояния заявки
CREATE sequence item_state_id_seq;
CREATE TABLE public.item_state (
	id int4 NOT NULL DEFAULT nextval('item_state_id_seq'),
	description text NOT NULL,
	CONSTRAINT item_state_pk PRIMARY KEY (id),
	CONSTRAINT item_state_un UNIQUE (description)
);
COMMENT ON TABLE public.item_state IS 'Состояние заявки';

--Заявки
CREATE sequence items_id_seq;
CREATE TABLE public.items (
	id int4 NOT NULL DEFAULT nextval('items_id_seq'),
	description text NOT NULL,
	user_id int4 NOT NULL,
	category_id int4 NOT NULL,
	req_state_id int4 NOT NULL,
	CONSTRAINT item_pk PRIMARY KEY (id),
	CONSTRAINT item_fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),
	CONSTRAINT item_fk_category_id FOREIGN KEY (category_id) REFERENCES category(id),
	CONSTRAINT item_fk_req_state_id FOREIGN KEY (req_state_id) REFERENCES item_state(id)
);
COMMENT ON TABLE public.items IS 'Заявка';

--Комментарии
CREATE sequence comments_id_seq;
CREATE TABLE public.comments (
	id int4 NOT NULL DEFAULT nextval('comments_id_seq'),
	"comment" text NOT NULL,
	item_id int4 NOT NULL,
	CONSTRAINT comments_pk PRIMARY KEY (id),
	CONSTRAINT comments_fk FOREIGN KEY (item_id) REFERENCES items(id)
);
COMMENT ON TABLE public.comments IS 'Комментарий к заявке';

--Вложения
CREATE sequence file_id_seq;
CREATE TABLE public.files (
	id int4 NOT NULL DEFAULT nextval('file_id_seq'),
	url varchar NOT NULL,
	item_id int4 NOT NULL,
	CONSTRAINT files_pk PRIMARY KEY (id),
	CONSTRAINT files_fk FOREIGN KEY (item_id) REFERENCES items(id)
);
COMMENT ON TABLE public.files IS 'Файлы приложенные к заявке';
