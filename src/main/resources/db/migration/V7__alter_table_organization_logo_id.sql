ALTER TABLE public.organization
    ADD CONSTRAINT fk_logo_id FOREIGN KEY (logo_id)
        REFERENCES public.uploads (id);