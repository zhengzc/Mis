package com.zzc.core.dataSourceManager;

/**
 * 上下文Holder
 *
 */
public class ContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static void setContext(String context){
        contextHolder.set(context);
    }
    
    public static String getContext(){
        return  contextHolder.get();
    }
    
    public static void clearContext(){
        contextHolder.remove();
    }
}
/*@SuppressWarnings("unchecked") 
public class ContextHolder<T> {

    private static final ThreadLocal contextHolder = new ThreadLocal();
    
    public static <T> void setContext(T context)
    {
        contextHolder.set(context);
    }
    
    public static <T> T getContext()
    {
        return (T) contextHolder.get();
    }
    
    public static void clearContext()
    {
        contextHolder.remove();
    }
}*/