CREATE DEFINER = 'root'@'localhost' PROCEDURE `getOneMinutePriceById`(
        IN SOL_ID INTEGER(11),
        OUT PRICE_ONE_MINUTE INTEGER(11)
    )
    NOT DETERMINISTIC
    READS SQL DATA
    SQL SECURITY DEFINER
    COMMENT ''
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
END;