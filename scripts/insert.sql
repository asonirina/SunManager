Insert into customers (customer_id, mobile, name) VALUES(1, '80291111111', 'Alina');
Insert into customers (customer_id, mobile, name) VALUES(2, '80291316547', 'Ira');
Insert into customers (customer_id, mobile, name) VALUES(3, '80174569871', 'Test');


Insert into abonements (abonement_code, price, customer_id, buy_date, end_date, minutes, duration) VALUES('B999', 20160, 1, '2013-07-04', '2013-10-04', 36, 3);
Insert into abonements (abonement_code, price, customer_id, buy_date, end_date, minutes, duration) VALUES('C1090', 80160, 2, '2013-01-10', '2013-04-10', 53, 3);
Insert into abonements (abonement_code, price, customer_id, buy_date, end_date, minutes, duration) VALUES('K901', 120160, 1, '2013-06-04', '2013-12-04', 109, 6);
Insert into abonements (abonement_code, price, customer_id, buy_date, end_date, minutes, duration) VALUES('B901', 20160, 3, '2013-06-04', '2013-09-04', 40, 3);
Insert into abonements (abonement_code, price, customer_id, buy_date, end_date, minutes, duration) VALUES('M704', 220160, 3, '2013-02-15', '2013-08-15', 150, 6);


Insert into users (login, name, password, role) VALUES('cofee',  'Nastya', 'root', 'derictor');
Insert into users (login, name, password, role) VALUES('admin1', 'Lena', '2', 'admin');
Insert into users (login, name, password, role) VALUES('admin2', 'Liza', '3', 'admin');

Insert into cosmetics (cosmetics_id, name, price, cosmetics_count) VALUES(1,  'Крем Dun', 20150, 20);
Insert into cosmetics (cosmetics_id, name, price, cosmetics_count) VALUES(2, 'Лосьен Dove', 10600, 3);
Insert into cosmetics (cosmetics_id, name, price, cosmetics_count) VALUES(3, 'Крем Pola', 15000, 5);


Insert into vertical_sun (vertical_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(1,  '2013-12-10', 36, 91730, 7800);
Insert into vertical_sun (vertical_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(2, '2013-12-11', 40, 53628, 7600);
Insert into vertical_sun (vertical_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(3, '2013-12-12', 45, 82749, 7500);

Insert into gorizontal_green_sun (gorizontal_green_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(1,  '2013-12-10', 21, 91732, 7800);
Insert into gorizontal_green_sun (gorizontal_green_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(2, '2013-12-11', 31, 53638, 7900);
Insert into gorizontal_green_sun (gorizontal_green_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(3, '2013-12-12', 22, 82799, 7800);

Insert into gorizontal_blue_sun (gorizontal_blue_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(1,  '2013-12-10', 32, 91730, 7700);
Insert into gorizontal_blue_sun (gorizontal_blue_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(2, '2013-12-11', 20, 53628, 7800);
Insert into gorizontal_blue_sun (gorizontal_blue_sun_id, start_date, total_minute, l2, one_minute_price) VALUES(3, '2013-12-12', 24, 82750, 7600);


Insert into vertical_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(1,  '2013-12-10', 6, 0, 'B999');
Insert into vertical_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(2,  '2013-12-10', 7, 54600, NULL);
Insert into vertical_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(3,  '2013-12-10', 10, 78000, NULL);

Insert into gorizontal_blue_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(1,  '2013-12-11', 5, 39500, NULL);
Insert into gorizontal_blue_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(2,  '2013-12-11', 7, 0, 'C1090');
Insert into gorizontal_blue_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(3,  '2013-12-11', 11, 0, 'K901');


Insert into gorizontal_green_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(1,  '2013-12-11', 5, 39500, NULL);
Insert into gorizontal_green_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(2,  '2013-12-11', 7, 54600, NULL);
Insert into gorizontal_green_sun_data (data_id, start_date, minutes, total_price, abonement_number) VALUES(3,  '2013-12-11', 11, 85800, NULL);