package com.greenko.order_analytics;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.greenko.model.Order;
import com.greenko.model.OrderStatus;

public class OrderAnalytics {

	
	public static double totalDeliveredRevenue(List<Order> orders) {
        return orders.stream()
                .filter(o -> o.getStatus() == OrderStatus.DELIVERED)
                .mapToDouble(Order::getAmount)
                .sum();
    }

    public static Map<String, Double> revenuePerCity(List<Order> orders) {
        return orders.stream()
                .filter(o -> o.getStatus() == OrderStatus.DELIVERED)
                .collect(Collectors.groupingBy(
                        Order::getCity,
                        Collectors.summingDouble(Order::getAmount)
                ));
    }

    public static Map<String, Double> top3CategoriesByRevenue(List<Order> orders) {
        return orders.stream()
                .filter(o -> o.getStatus() == OrderStatus.DELIVERED)
                .collect(Collectors.groupingBy(
                        Order::getCategory,
                        Collectors.summingDouble(Order::getAmount)
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
	
	
}
