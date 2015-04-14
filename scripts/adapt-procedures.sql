drop procedure if exists menu_hier;

delimiter #

create procedure menu_hier
(
in p_menu_id VARCHAR(50),
in p_user_role  VARCHAR(15)
)
begin

declare v_done tinyint unsigned default 0;
declare v_depth smallint unsigned default 0;

drop temporary table if exists adapt_hier;

create temporary table adapt_hier(
 parent_menu_id      VARCHAR(50),
 menu_id VARCHAR(50),
 depth smallint unsigned default 0
)engine = memory;

insert into adapt_hier select parent_menu_id, menu_id, v_depth from standart_menu where menu_id = p_menu_id and user_role = p_user_role;

drop temporary table if exists adapt_tmp;

create temporary table adapt_tmp engine=memory select * from adapt_hier;

while not v_done do

    if exists( select 1 from standart_menu p inner join adapt_hier on p.parent_menu_id = adapt_hier.menu_id and adapt_hier.depth = v_depth) then

        insert into adapt_hier
            select p.parent_menu_id, p.menu_id, v_depth + 1 from standart_menu p
            inner join adapt_tmp on p.parent_menu_id = adapt_tmp.menu_id and adapt_tmp.depth = v_depth;

        set v_depth = v_depth + 1;

        truncate table adapt_tmp;
        insert into adapt_tmp select * from adapt_hier where depth = v_depth;

    else
        set v_done = 1;
    end if;

end while;

select DISTINCT
 p.menu_id,
 p.description as menu_name,
 b.menu_id as parent_menu_id,
 b.description as parent_menu_name,
 adapt_hier.depth
from
 adapt_hier
inner join standart_menu p on adapt_hier.menu_id = p.menu_id
left outer join standart_menu b on adapt_hier.parent_menu_id = b.menu_id
order by
 adapt_hier.depth;

drop temporary table if exists adapt_hier;
drop temporary table if exists adapt_tmp;

end #