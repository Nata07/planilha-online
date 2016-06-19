package br.com.caelum.vraptor.controller;

import br.com.caelum.vraptor.*;
import jdk.nashorn.internal.ir.annotations.Reference;

import javax.inject.Inject;

/**
 * Created by Asus on 19/06/2016.
 */
@Reference
public class ModalController {

    private final Result result;

    protected ModalController(){this(null);}

    @Inject
    public ModalController(Result result){this.result = result;}

    @Path("/")
    public void modal(){

    }
    @Get("/abrirModalSenha")
    public void abrirModalSenha(){
        result.forwardTo(this).modal();
    }

}
