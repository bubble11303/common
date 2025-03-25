package transaction.impl;

import java.util.function.Consumer;

import javax.annotation.Resource;

import exception.BizException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.extern.slf4j.Slf4j;
import transaction.LocalTransaction;

/**
 * 本地事务模板，需要编程式事务的调用execute方法
 * <p>
 * 不要在execute内部的action中catch掉异常，否则事务将不会rollback
 */
@Slf4j
@Service
public class LocalTransactionImpl implements LocalTransaction {

    @Resource
    private TransactionTemplate template;

    @Override
    public <T> T execute(TransactionCallback<T> action) throws BizException {
        long start = System.currentTimeMillis();
        try {
            T execute = template.execute(action);
            log.info("返回值事务【{}】-----> success", action.getName());
            return execute;
        } catch (Exception e) {
            log.error("事务异常, transaction name:【{}】", action.getName(), e);
            throw e;
        } finally {
            long useTime = System.currentTimeMillis() - start;
            log.info("返回值事务已结束, 事务名称:【{}】, 用时 (ms):【{}】", action.getName(), useTime);
        }
    }

    @Override
    public void execute(TransactionCallbackWithoutResult action) throws BizException {
        long start = System.currentTimeMillis();
        try {
            template.execute(action);
            log.info("无返回值事务【{}】-----> success", action.getName());
        } catch (Exception e) {
            log.error("无返回值事务异常, transaction name: 【{}】", action.getName(), e);
            throw e;
        } finally {
            long useTime = System.currentTimeMillis() - start;
            log.info("无返回值事务已结束, 事务名称:【{}】, 用时 (ms):【{}】", action.getName(), useTime);
        }
    }

    @Resource
    private PlatformTransactionManager transactionManager;

    @Override
    public <T> boolean execute(String name, Consumer<T> consumer) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            consumer.accept(null);
            transactionManager.commit(status);
            log.info("事务【{}】-----> success", name);
            return true;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("事务【{}】 异常回滚------>", name, e);
            return false;
        }
    }

}
