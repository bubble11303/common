package transaction;

import exception.BizException;
import transaction.impl.TransactionCallback;
import transaction.impl.TransactionCallbackWithoutResult;

import java.util.function.Consumer;


public interface LocalTransaction {

    /**
     * 事务 有返回值
     */
    <T> T execute(TransactionCallback<T> action) throws BizException;

    /**
     * 事务 无返回值
     */
    void execute(TransactionCallbackWithoutResult action) throws BizException;

    <T> boolean execute(String name, Consumer<T> consumer);
}
