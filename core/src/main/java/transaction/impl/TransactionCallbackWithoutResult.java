package transaction.impl;

import lombok.Getter;

@Getter
public abstract class TransactionCallbackWithoutResult extends
        org.springframework.transaction.support.TransactionCallbackWithoutResult {

    /**
     * 本地事务名称
     */
    private final String name;

    public TransactionCallbackWithoutResult(String name) {
        this.name = name;
    }

}
