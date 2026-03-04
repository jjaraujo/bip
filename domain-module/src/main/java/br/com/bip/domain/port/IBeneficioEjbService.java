package br.com.bip.domain.port;

import br.com.bip.domain.BeneficioEntity;

import br.com.bip.domain.exception.EjbAppException;
import jakarta.ejb.Local;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Local
public interface IBeneficioEjbService {

    BeneficioEntity create(@NotNull BeneficioEntity beneficio);

    BeneficioEntity update(@NotNull Long id, @NotNull BeneficioEntity beneficio);

    void delete(@NotNull Long id);

    BeneficioEntity findById(@NotNull Long id);

    List<BeneficioEntity> listAll();

    void transfer(@NotNull Long fromId, @NotNull Long toId, @NotNull BigDecimal amount) throws EjbAppException;
}
