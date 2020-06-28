package com.ironhack.interfaces.Int;

import java.math.BigDecimal;

public interface Transactional {
    BigDecimal increaseAmount(BigDecimal amount);
    BigDecimal decreaseAmount(BigDecimal amount);
    String toString();
}
