delete from action_info;
delete from standart_menu;

Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('abonementsMenu', NULL, 'Абнементы', 'admin');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('addAbonementItem', 'abonementsMenu', 'Доступные абонементы', 'admin');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('residueItem', 'abonementsMenu', 'Остаток минут', 'admin');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('findAbonByPhoneItem', 'abonementsMenu', 'Поиск абонементов', 'admin');

Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('commentsMenu', NULL, 'Комментарии', 'admin');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('addCommentItem', 'commentsMenu', 'Добавить комментарии', 'admin');

Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('statisticsMenu', NULL, 'Дополнительно', 'admin');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('bankItem', 'statisticsMenu', 'Дополнительная информация', 'admin');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('setBankPerDayItem', 'statisticsMenu', 'Внести кассу', 'admin');


Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('abonementsMenu', NULL, 'Абнементы', 'director');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('addAbonementItem', 'abonementsMenu', 'Доступные абонементы', 'director');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('residueItem', 'abonementsMenu', 'Остаток минут', 'director');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('findAbonByPhoneItem', 'abonementsMenu', 'Поиск абонементов', 'director');

Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('cosmeticsMenu', NULL, 'Косметика', 'director');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('addCosmItem', 'cosmeticsMenu', 'Добавить косметику', 'director');

Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('usersMenu', NULL, 'Пользователи', 'director');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('administratorsItem', 'usersMenu', 'Администраторы', 'director');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('customersItem', 'usersMenu', 'Клиенты', 'director');

Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('commentsMenu', NULL, 'Комментарии', 'director');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('showCommentsItem', 'commentsMenu', 'Показать комментарии', 'director');

Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('statisticsMenu', NULL, 'Дополнительно', 'director');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('bankItem', 'statisticsMenu', 'Дополнительная информация', 'director');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('statisticsItem', 'statisticsMenu', 'Статистика', 'director');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('periodDataItem', 'statisticsMenu', 'Данные за период', 'director');