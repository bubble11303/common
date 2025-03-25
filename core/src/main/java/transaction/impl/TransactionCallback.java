package transaction.impl;

import lombok.Getter;

@Getter
public abstract class TransactionCallback<T> implements org.springframework.transaction.support.TransactionCallback<T> {

    /**
     * 本地事务名称
     */
    private final String name;

    public TransactionCallback(String name) {
        this.name = name;
    }

}
