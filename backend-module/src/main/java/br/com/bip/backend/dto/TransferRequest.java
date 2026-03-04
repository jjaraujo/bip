package br.com.bip.backend.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record TransferRequest (

    @NotNull Long fromId,

    @NotNull Long toId,

    @NotNull BigDecimal valor){

}
