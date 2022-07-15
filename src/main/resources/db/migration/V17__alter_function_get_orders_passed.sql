create or replace function get_orders_by_user_id(v_user_id bigint) returns text
    language plpgsql
as
$$
DECLARE
    r         record;
    data_json jsonb := '[]';
BEGIN
    for r in select o.*,
                    (select json_agg(s.*)
                     from order_service os
                              inner join service s on s.id = os.service_id
                     where os.order_id = o.id)      as services,
                    (select row_to_json(b.*)
                     from barber_shop b
                     where b.id = o.barber_shop_id) as barbershop
             from orders o
             where o.deleted = false
               and o.order_time <= now()
               and o.client_id = v_user_id
        loop

            data_json = data_json || jsonb_agg(r);
        end loop;

    return data_json::text;
END
$$;