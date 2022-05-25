package uz.pdp.cutecutapp.controller;

import uz.pdp.cutecutapp.services.BaseService;

public abstract class AbstractController<S extends BaseService> {
    protected final static String API = "/api";
    protected final static String VERSION = "/v1";
    protected final static String PATH = API + VERSION;

    protected final S service;


    protected AbstractController(S service) {
        this.service = service;
    }
}
