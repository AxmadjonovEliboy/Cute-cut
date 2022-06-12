create or replace function get_avg_rate_of_barber_shop(v_barber_shop_id int8)
    returns double precision
    language plpgsql
as
$$
DECLARE
    sum    integer := 0;
    count  integer := 0;
    v_item integer;

BEGIN
    for v_item in select r.rate from rating r where r.barber_shop_id = v_barber_shop_id
        loop
            sum = sum + v_item;
            count = count + 1;
        end loop;
    if count != 0 then
        return sum::double precision / count::double precision;
    else
        return 0::double precision;
    end if;
END
$$;