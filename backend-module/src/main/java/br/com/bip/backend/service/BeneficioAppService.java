package br.com.bip.backend.service;

import br.com.bip.backend.dto.BeneficioDto;
import br.com.bip.backend.dto.TransferRequest;
import br.com.bip.domain.BeneficioEntity;
import br.com.bip.domain.exception.EjbAppException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficioAppService {

    private final BeneficioEjbGateway gateway;

    public BeneficioAppService(BeneficioEjbGateway gateway) {
        this.gateway = gateway;
    }

    public List<BeneficioEntity> listAll() {
        return gateway.ejb().listAll();
    }

    public BeneficioEntity findById(Long id) {
        return gateway.ejb().findById(id);
    }

    public BeneficioEntity create(BeneficioDto dto) {
        BeneficioEntity e = toEntity(dto);
        e.setId(null);
        return gateway.ejb().create(e);
    }

    public BeneficioEntity update(Long id, BeneficioDto dto) {
        BeneficioEntity e = toEntity(dto);
        return gateway.ejb().update(id, e);
    }

    public void delete(Long id) {
        gateway.ejb().delete(id);
    }

    public void transfer(TransferRequest req) throws EjbAppException {
        gateway.ejb().transfer(req.fromId(), req.toId(), req.valor());
    }

    private static BeneficioEntity toEntity(BeneficioDto dto) {
        BeneficioEntity e = new BeneficioEntity();
        e.setNome(dto.nome());
        e.setDescricao(dto.descricao());
        e.setValor(dto.valor());
        e.setAtivo(dto.ativo());
        return e;
    }
}
