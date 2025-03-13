package model;

/**
 * @Author: 热心村民王富贵
 * @Date: 2025/03/13
 * @Description:
 */

public class CurrentContext {

    /**
     * 保存用户对象的ThreadLocal
     */
    private static final ThreadLocal<RuntimeContext> threadLocal = new ThreadLocal<>();

    /**
     * 添加当前登录用户方法
     */
    public static void setContext(RuntimeContext context) {
        threadLocal.set(context);
    }


    public static RuntimeContext getContext() {
        return threadLocal.get();
    }


    /**
     * 防止内存泄漏
     */
    public static void remove() {
        threadLocal.remove();
    }

}
