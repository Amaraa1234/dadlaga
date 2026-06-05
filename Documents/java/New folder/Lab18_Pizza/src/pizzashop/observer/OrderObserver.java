package pizzashop.observer;

import pizzashop.Order;

public interface OrderObserver {
    void onNewOrder(Order order);
}