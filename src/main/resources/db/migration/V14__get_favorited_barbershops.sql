create or replace function get_favorited_barber_shop(v_id int8)
    returns text
    language plpgsql
as
$$
DECLARE
    r         record;
    data_json jsonb := '[]';
BEGIN

    for r in select bs.*
             from favorites f
                      inner join barber_shop bs on f.barber_shop_id = bs.id
             where f.
                       client_id = v_id
        loop
            data_json = data_json || json_agg(r)::jsonb;
        end loop;
    return data_json::text;
END
$$