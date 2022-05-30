ALTER TABLE public.favorites
    ADD CONSTRAINT fk_client_id FOREIGN KEY (client_id)
        REFERENCES public.auth_user (id);