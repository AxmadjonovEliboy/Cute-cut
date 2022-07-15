ALTER TABLE public.rating
    ADD CONSTRAINT fk_barber_shop_id_bookstore FOREIGN KEY (barber_shop_id)
        REFERENCES public.barber_shop (id);


ALTER TABLE public.rating
    ADD CONSTRAINT fk_client_id_bookstore FOREIGN KEY (client_id)
        REFERENCES public.auth_user (id);