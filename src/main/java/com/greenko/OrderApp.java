package com.greenko;

import java.util.List;

import com.greenko.model.Order;
import com.greenko.order_analytics.OrderAnalytics;
import com.greenko.util.OrderLoader;

public class OrderApp {
	
	public static void main(String[] args) {
		List<Order> orders = OrderLoader.loadOrders("orders.csv");

        System.out.println("Total Delivered Revenue:" + OrderAnalytics.totalDeliveredRevenue(orders));
//        System.out.println(OrderAnalytics.totalDeliveredRevenue(orders));

        System.err.println("\n\nRevenue Per City:");
        OrderAnalytics.revenuePerCity(orders).forEach((city, revenue) ->System.out.println(city + " : " + revenue));

        System.err.println("\nTop 3 Categories By Revenue:");
        OrderAnalytics.top3CategoriesByRevenue(orders).forEach((category, revenue) ->System.out.println(category + " : " + revenue));
    }

	

}
