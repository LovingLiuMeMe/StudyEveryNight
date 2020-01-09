package cn.lovingliu.example.component;

import cn.lovingliu.example.enums.DBTypeEnum;

/**
 * @Author：LovingLiu
 * @Description: 通过ThreadLocal将数据源设置到每个线程上下文中
 * @Date：Created in 2020-01-09
 */
public class DBContextHolder {
    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    public static void set(DBTypeEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
        System.out.println("切换到master");
    }

    public static void slave() {
        set(DBTypeEnum.SLAVE);
        System.out.println("切换到slave");
    }
}
