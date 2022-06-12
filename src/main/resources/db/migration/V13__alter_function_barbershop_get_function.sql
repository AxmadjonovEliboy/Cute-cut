create or replace function public.get_barbershops_by_criteria(v_latitude double precision, v_longitude double precision,
                                                              distance integer)
    returns text
    language plpgsql
as
$$
DECLARE
    r        record;
    dataJson jsonb := '[]';
    dis      numeric;
BEGIN
    for r in
        select b.*,
               public.get_avg_rate_of_barber_shop(b.id)      as rating,
               (3959 * acos(cos(radians(v_latitude)) * cos(radians(b.latitude))
                                * cos(radians(b.longitude) - radians(v_longitude)) + sin(radians(v_latitude))
                                * sin(radians(b.latitude)))) as distance
        from barber_shop b
        where (3959 * acos(cos(radians(v_latitude)) * cos(radians(b.latitude))
                               * cos(radians(b.longitude) - radians(v_longitude)) + sin(radians(v_latitude))
                               * sin(radians(b.latitude)))) < distance/* limit :limit offset :offset*/

        loop
            dataJson = dataJson || json_agg(r)::jsonb;
        end loop;

    return dataJson::text;

END
$$;