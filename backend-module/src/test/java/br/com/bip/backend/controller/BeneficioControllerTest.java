package br.com.bip.backend.controller;

import br.com.bip.backend.service.BeneficioAppService;
import br.com.bip.domain.BeneficioEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeneficioController.class)
class BeneficioControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BeneficioAppService service;

    @Test
    void listAll_return200ContainsA() throws Exception {
        BeneficioEntity b = new BeneficioEntity();
        b.setId(1L);
        b.setNome("Beneficio A");
        b.setValor(new BigDecimal("10.00"));

        when(service.listAll()).thenReturn(List.of(b));

        mvc.perform(get("/v1/beneficios"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Beneficio A")));
    }
}
