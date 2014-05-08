Insert into customers (customer_id, mobile, name) VALUES(1, '80291111111', 'Alina');
Insert into customers (customer_id, mobile, name) VALUES(2, '80291316547', 'Ira');
Insert into customers (customer_id, mobile, name) VALUES(3, '80174569871', 'Test');

Insert into users (login, name, password, role) VALUES('cofee',  'Nastya', 'root', 'derictor');
Insert into users (login, name, password, role) VALUES('admin1', 'Lena', '2', 'admin');
Insert into users (login, name, password, role) VALUES('admin2', 'Liza', '3', 'admin');

Insert into cosmetics (cosmetics_id, name, price, cosmetics_count) VALUES(1,  'Крем Dun', 20150, 20);
Insert into cosmetics (cosmetics_id, name, price, cosmetics_count) VALUES(2, 'Лосьен Dove', 10600, 3);
Insert into cosmetics (cosmetics_id, name, price, cosmetics_count) VALUES(3, 'Крем Pola', 15000, 5);
Insert into cosmetics (cosmetics_id, name, price, cosmetics_count) VALUES(4, 'stikini', 1000, 1000000);

Insert into vertical_sun (vertical_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(1,  '2013-12-10', 36, 0, 7800);
Insert into vertical_sun (vertical_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(2, '2013-12-11', 40, 0, 7600);
Insert into vertical_sun (vertical_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(3, '2013-12-12', 45, 0, 7500);

Insert into gorizontal_green_sun (gorizontal_green_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(1,  '2013-12-10', 21, 0, 7800);
Insert into gorizontal_green_sun (gorizontal_green_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(2, '2013-12-11', 31, 0, 7900);
Insert into gorizontal_green_sun (gorizontal_green_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(3, '2013-12-12', 22, 0, 7800);

Insert into gorizontal_blue_sun (gorizontal_blue_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(1,  '2013-12-10', 32, 0, 7700);
Insert into gorizontal_blue_sun (gorizontal_blue_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(2, '2013-12-11', 20, 0, 7800);
Insert into gorizontal_blue_sun (gorizontal_blue_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(3, '2013-12-12', 24, 0, 7600);


Insert into vertical_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(1,  '2013-12-10', 6, 0, 'B999');
Insert into vertical_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(2,  '2013-12-10', 7, 54600, NULL);
Insert into vertical_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(3,  '2013-12-10', 10, 78000, NULL);

Insert into gorizontal_blue_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(1,  '2013-12-11', 5, 39500, NULL);
Insert into gorizontal_blue_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(2,  '2013-12-11', 7, 0, 'C1090');
Insert into gorizontal_blue_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(3,  '2013-12-11', 11, 0, 'K901');


Insert into gorizontal_green_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(1,  '2013-12-11', 5, 39500, NULL);
Insert into gorizontal_green_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(2,  '2013-12-11', 7, 54600, NULL);
Insert into gorizontal_green_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(3,  '2013-12-11', 11, 85800, NULL);


Insert into comments_data (comment_id, start_date, comment) VALUES(1,  '2013-12-11', 'dsadsadasdsad');
Insert into comments_data (comment_id, start_date, comment) VALUES(2,  '2013-12-11', 'v vvvvv');
Insert into comments_data (comment_id, start_date, comment) VALUES(3,  '2013-12-11', 'efergfgdgfdg');



Insert into available_abonements (letter, price, minutes) VALUES('B', 180000, 40, 1);
Insert into available_abonements (letter, price, minutes) VALUES('C', 264000, 60, 1);
Insert into available_abonements (letter, price, minutes) VALUES('D', 420000, 100, 1);
Insert into available_abonements (letter, price, minutes) VALUES('K', 490000, 120, 1);
Insert into available_abonements (letter, price, minutes) VALUES('M', 600000, 150, 1);
Insert into available_abonements (letter, price, minutes) VALUES('O', 140000, 40, 1);
Insert into available_abonements (letter, price, minutes) VALUES('G', 204000, 60, 1);
Insert into available_abonements (letter, price, minutes) VALUES('R', 320000, 100, 1);
Insert into available_abonements (letter, price, minutes) VALUES('H', 360000, 120, 1);


Insert into available_abonements (letter, price, minutes) VALUES('SB', 81000, 40, 1);
Insert into available_abonements (letter, price, minutes) VALUES('SC', 119000, 60, 1);
Insert into available_abonements (letter, price, minutes) VALUES('SD', 189000, 100, 1);
Insert into available_abonements (letter, price, minutes) VALUES('SK', 220500, 120, 1);
Insert into available_abonements (letter, price, minutes) VALUES('SM', 270000, 150, 1);
Insert into available_abonements (letter, price, minutes) VALUES('SO', 63000, 40, 1);
Insert into available_abonements (letter, price, minutes) VALUES('SG', 92000, 60, 1);
Insert into available_abonements (letter, price, minutes) VALUES('SR', 144000, 100, 1);
Insert into available_abonements (letter, price, minutes) VALUES('SH', 162000, 120, 1);
