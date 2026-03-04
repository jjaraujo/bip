package br.com.bip.backend.service;

import br.com.bip.domain.port.IBeneficioEjbService;
import org.springframework.stereotype.Component;

@Component
public class BeneficioEjbGateway {

    private final IBeneficioEjbService ejb;

    public BeneficioEjbGateway(IBeneficioEjbService ejb) {
        this.ejb = ejb;
    }

    public IBeneficioEjbService ejb() {
        return ejb;
    }
}
