ALTER TABLE public.barber_shop
    ADD CONSTRAINT fk_organization_id FOREIGN KEY (org_id)
        REFERENCES public.organization (id);