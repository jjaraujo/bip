package br.com.bip.ejb;

import br.com.bip.domain.BeneficioEntity;
import br.com.bip.domain.exception.EjbAppException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BeneficioEjbServiceTest {

    @Mock
    private EntityManager em;

    @InjectMocks
    private BeneficioEjbService service;

    private BeneficioEntity from;
    private BeneficioEntity to;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        from = new BeneficioEntity();
        from.setId(1L);
        from.setValor(new BigDecimal("100"));

        to = new BeneficioEntity();
        to.setId(2L);
        to.setValor(new BigDecimal("50"));
    }

    @Test
    void transfer_deveTransferirComSucesso() throws EjbAppException {

        when(em.find(BeneficioEntity.class, 1L, LockModeType.PESSIMISTIC_WRITE))
                .thenReturn(from);

        when(em.find(BeneficioEntity.class, 2L, LockModeType.PESSIMISTIC_WRITE))
                .thenReturn(to);

        service.transfer(1L, 2L, new BigDecimal("30"));

        assertEquals(new BigDecimal("70"), from.getValor());
        assertEquals(new BigDecimal("80"), to.getValor());

        verify(em).merge(from);
        verify(em).merge(to);
    }

    @Test
    void transfer_deveLancarErroQuandoSaldoInsuficiente() {

        when(em.find(BeneficioEntity.class, 1L, LockModeType.PESSIMISTIC_WRITE)).thenReturn(from);

        when(em.find(BeneficioEntity.class, 2L, LockModeType.PESSIMISTIC_WRITE)).thenReturn(to);

        assertThrows(
                EjbAppException.class,
                () -> service.transfer(1L, 2L, new BigDecimal("150"))
        );

        verify(em, never()).merge(from);
        verify(em, never()).merge(to);
    }
}