package br.com.bip.ejb;

import br.com.bip.domain.BeneficioEntity;
import br.com.bip.domain.port.IBeneficioEjbService;
import br.com.bip.domain.exception.EjbException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@Stateless
public class BeneficioEjbService implements IBeneficioEjbService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public BeneficioEntity create(@NotNull BeneficioEntity beneficio) {
        em.persist(beneficio);
        return beneficio;
    }

    @Override
    public BeneficioEntity update(@NotNull Long id, @NotNull BeneficioEntity beneficio) {
        BeneficioEntity entity = em.find(BeneficioEntity.class, id);
        entity.setNome(beneficio.getNome());
        entity.setDescricao(beneficio.getDescricao());
        entity.setValor(beneficio.getValor());
        entity.setAtivo(beneficio.isAtivo());
        return em.merge(entity);
    }

    @Override
    public void delete(@NotNull Long id) {
        BeneficioEntity entity = em.find(BeneficioEntity.class, id);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public BeneficioEntity findById(@NotNull Long id) {
        return em.find(BeneficioEntity.class, id);
    }

    @Override
    public List<BeneficioEntity> listAll() {
        return em.createQuery("from BeneficioEntity", BeneficioEntity.class)
                .getResultList();
    }

    @Override
    public void transfer(@NotNull Long fromId, @NotNull Long toId, @NotNull BigDecimal valor) throws EjbException {

        BeneficioEntity from = em.find(
                BeneficioEntity.class,
                fromId,
                LockModeType.PESSIMISTIC_WRITE
        );

        BeneficioEntity to = em.find(
                BeneficioEntity.class,
                toId,
                LockModeType.PESSIMISTIC_WRITE
        );

        from.setValor(from.getValor().subtract(valor));
        if(from.getValor().doubleValue() < 0) {
            throw new EjbException("Saldo indispoível para transferencia");
        }
        to.setValor(to.getValor().add(valor));

        em.merge(from);
        em.merge(to);
    }
}