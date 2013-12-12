DELIMITER $$

DROP PROCEDURE IF EXISTS `getOneMinutePriceById` $$
CREATE PROCEDURE `getOneMinutePriceById`(
        IN SOL_ID INTEGER(11),
        OUT PRICE_ONE_MINUTE INTEGER(11)
        )
BEGIN
CASE
WHEN SOL_ID = 1 THEN
    Select one_minute_price into PRICE_ONE_MINUTE from vertical_sun
    where start_date = (select MAX(start_date) from vertical_sun);
WHEN SOL_ID = 2 THEN
    Select one_minute_price into PRICE_ONE_MINUTE from gorizontal_blue_sun
    where start_date = (select MAX(start_date) from gorizontal_blue_sun);
WHEN SOL_ID = 3 THEN
    Select one_minute_price into PRICE_ONE_MINUTE from gorizontal_green_sun
    where start_date = (select MAX(start_date) from gorizontal_green_sun);
END CASE;
END $$

//----------------------------------------------------------------------------------------
DROP PROCEDURE IF EXISTS `checkUser` $$
CREATE PROCEDURE `checkUser`(
        IN LOGIN VARCHAR(15),
        IN PASSWORD VARCHAR(15),
        OUT EXIST BOOLEAN
        )
BEGIN
DECLARE user_name VARCHAR(150);
SET EXIST = false;

Select name INTO user_name from users where user_id = LOGIN and password = PASSWORD;
IF user_name != NULL THEN
   SET EXIST = true;
END IF;
END $$

DELIMITER ;