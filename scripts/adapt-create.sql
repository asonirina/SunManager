CREATE TABLE IF NOT EXISTS action_info (
      action_info_id    INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
      menu_id           VARCHAR(50) NOT NULL,
      user_role         VARCHAR(15),
      action_date       date
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS standart_menu (
      action_info_id      INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
      menu_id             VARCHAR(50) NOT NULL,
      parent_menu_id      VARCHAR(50),
      description         VARCHAR(100),
      user_role           VARCHAR(15)
) ENGINE=INNODB;