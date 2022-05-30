ALTER TABLE public.favorites
    ADD CONSTRAINT fk_barber_shop_id FOREIGN KEY (barber_shop_id)
        REFERENCES public.barber_shop (id);