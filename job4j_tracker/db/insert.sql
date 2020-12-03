INSERT INTO public.rules ("name",description) VALUES
('добавление','добавление заявки в систему'),
('удаление','удаление заявки из системы'),
('редактирование','редактирование заявки системы'),
('просмотр','просмотр всех заявок системы');

INSERT INTO public.roles ("name",description) VALUES
('Администратор','Администратор системы заявок'),
('Пользователь','Пользователь системы заявок');

INSERT INTO public.rolesrules (role_id,rule_id) VALUES
((select id from public.roles where "name" = 'Администратор'),(select id from public.rules where "name" = 'добавление')),
((select id from public.roles where "name" = 'Администратор'),(select id from public.rules where "name" = 'удаление')),
((select id from public.roles where "name" = 'Администратор'),(select id from public.rules where "name" = 'редактирование')),
((select id from public.roles where "name" = 'Администратор'),(select id from public.rules where "name" = 'просмотр')),
((select id from public.roles where "name" = 'Пользователь'),(select id from public.rules where "name" = 'редактирование')),
((select id from public.roles where "name" = 'Пользователь'),(select id from public.rules where "name" = 'просмотр'));

INSERT INTO public.users (username,role_id) VALUES
('Иван Иванов', (select id from public.roles where "name" = 'Администратор')),
('Петр Петров', (select id from public.roles where "name" = 'Администратор'));

INSERT INTO public.category (description) VALUES
('гарантийный ремонт'),
('платный ремонт');

INSERT INTO public.item_state (description) VALUES
('принята'),
('в работе'),
('выполнена');

INSERT INTO public.items (description,category_id,req_state_id,user_id) VALUES
('Монитор гаснет через 5 минут работы и не включается',
(select id from public.users where username = 'Петр Петров'),
(select id from public.category where description = 'гарантийный ремонт'),
(select id from public.item_state where description = 'принята'));

INSERT INTO public.comments (comment,item_id) VALUES
('Монитор на экспертизе, возможно попадание влаги\воды',1);

INSERT INTO public.files (url,item_id) VALUES
('cloud.ru/46fb3b09-4d39-4c8a-bd49-4749a951fafa',1);
