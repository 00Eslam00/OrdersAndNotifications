package com.FCAI.OrdersAndNotifications.Utils;

public class MyThreadLocalHolder {
    private static final ThreadLocal<Boolean> inOrderList = ThreadLocal.withInitial(() -> false);

    public static boolean isInOrderList() {
        return inOrderList.get();
    }

    public static void setInOrderList(boolean value) {
        inOrderList.set(value);
    }

    public static void clearInOrderList() {
        inOrderList.remove();
    }
}
