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

DROP PROCEDURE IF EXISTS `checkUser` $$
CREATE PROCEDURE `checkUser`(
        IN LOGIN_U VARCHAR(15),
        IN PSWD VARCHAR(15),
        OUT EXIST BOOLEAN
        )
BEGIN
DECLARE user_name VARCHAR(15);
SET EXIST = false;

Select role INTO user_name from users where login = LOGIN_U and password = PSWD;
IF user_name IS NOT NULL THEN
   SET EXIST = true;
END IF;
END $$

DROP PROCEDURE IF EXISTS `getCodeBySymbol` $$
CREATE PROCEDURE `getCodeBySymbol`(
        IN SYMBOL VARCHAR(12),
        OUT CODE VARCHAR(12)
        )
BEGIN
SELECT MAX(code) INTO CODE from abonements_data
where letter = SYMBOL;
END $$

DROP PROCEDURE IF EXISTS `getL2ById` $$
CREATE PROCEDURE `getL2ById`(
        IN SOL_ID INTEGER(11),
        OUT L_2 DOUBLE
        )
BEGIN
CASE
WHEN SOL_ID = 1 THEN
    Select l2 into L_2 from vertical_sun
    where start_date = (select MAX(start_date) from vertical_sun);
WHEN SOL_ID = 2 THEN
    Select l2 into L_2 from gorizontal_blue_sun
    where start_date = (select MAX(start_date) from gorizontal_blue_sun);
WHEN SOL_ID = 3 THEN
    Select l2 into L_2 from gorizontal_green_sun
    where start_date = (select MAX(start_date) from gorizontal_green_sun);
END CASE;
END $$

DELIMITER ;