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
VALUES('abonementsMenu', NULL, 'Абнементы', 'derictor');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('addAbonementItem', 'abonementsMenu', 'Доступные абонементы', 'derictor');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('residueItem', 'abonementsMenu', 'Остаток минут', 'derictor');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('findAbonByPhoneItem', 'abonementsMenu', 'Поиск абонементов', 'derictor');

Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('cosmeticsMenu', NULL, 'Косметика', 'derictor');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('addCosmItem', 'cosmeticsMenu', 'Добавить косметику', 'derictor');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('addCosmItem1', 'addCosmItem', 'Добавить косметику2', 'derictor');

Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('usersMenu', NULL, 'Пользователи', 'derictor');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('administratorsItem', 'usersMenu', 'Администраторы', 'derictor');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('customersItem', 'usersMenu', 'Клиенты', 'derictor');

Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('commentsMenu', NULL, 'Комментарии', 'derictor');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('showCommentsItem', 'commentsMenu', 'Показать комментарии', 'derictor');

Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('statisticsMenu', NULL, 'Дополнительно', 'derictor');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('bankItem', 'statisticsMenu', 'Дополнительная информация', 'derictor');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('statisticsItem', 'statisticsMenu', 'Статистика', 'derictor');
Insert into standart_menu (menu_id, parent_menu_id, description, user_role)
VALUES('periodDataItem', 'statisticsMenu', 'Данные за период', 'derictor');