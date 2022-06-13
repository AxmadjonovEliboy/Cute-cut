create or replace function get_orders_by_user_id(v_user_id int8)
    returns text
    language plpgsql
as
$$
DECLARE
    r         record;
    r2        record;
    data_json jsonb := '[]';
BEGIN
    for r in select o.*,
                    (select json_agg(s.*)
                     from order_service os
                              inner join service s on s.id = os.service_id
                     where os.order_id = o.id) as services
             from orders o
             where o.deleted = false
               and o.client_id = v_user_id
        loop

            data_json = data_json || jsonb_agg(r);
--                         jsonb_concat(jsonb_agg(r), jsonb_agg(r2));
        end loop;

    return data_json::text;
END
$$;