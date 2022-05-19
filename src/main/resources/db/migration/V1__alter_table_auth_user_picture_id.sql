ALTER TABLE public.auth_user
    ADD CONSTRAINT fk_picture_id_bookstore FOREIGN KEY (picture_id)
        REFERENCES public.uploads (id);