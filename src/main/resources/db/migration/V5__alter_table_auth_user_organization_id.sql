ALTER TABLE public.auth_user
    ADD CONSTRAINT fk_organization_id FOREIGN KEY (organization_id)
        REFERENCES public.organization (id);