package com.greenko;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.greenko.model.ExecutorService;
import com.greenko.model.Order;
import com.greenko.order_analytics.OrderAnalytics;
import com.greenko.util.OrderLoader;

public class OrderApp {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		List<Order> orders = OrderLoader.loadOrders("orders.csv");

        System.out.println("Total Delivered Revenue:" + OrderAnalytics.totalDeliveredRevenue(orders));
//        System.out.println(OrderAnalytics.totalDeliveredRevenue(orders));

        System.err.println("\n\nRevenue Per City:");
        OrderAnalytics.revenuePerCity(orders).forEach((city, revenue) ->System.out.println(city + " : " + revenue));

        System.err.println("\nTop 3 Categories By Revenue:");
        OrderAnalytics.top3CategoriesByRevenue(orders).forEach((category, revenue) ->System.out.println(category + " : " + revenue));
        
        
        ExecutorService.runInParallel(orders);

    }

	

}
