package com.greenko.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.greenko.order_analytics.OrderAnalytics;

public class ExecutorService {
	public static void runInParallel(List<Order> orders)
            throws InterruptedException, ExecutionException {

        java.util.concurrent.ExecutorService executorService = Executors.newFixedThreadPool(3);

        Callable<Double> totalRevenueTask =
                () -> OrderAnalytics.totalDeliveredRevenue(orders);

        Callable<Map<String, Double>> revenuePerCityTask =
                () -> OrderAnalytics.revenuePerCity(orders);

        Callable<Map<String, Double>> topCategoriesTask =
                () -> OrderAnalytics.top3CategoriesByRevenue(orders);

        Future<Double> totalRevenueFuture =
                executorService.submit(totalRevenueTask);

        Future<Map<String, Double>> revenuePerCityFuture =
                executorService.submit(revenuePerCityTask);

        Future<Map<String, Double>> topCategoriesFuture =
                executorService.submit(topCategoriesTask);

        System.out.println("Total Delivered Revenue: "
                + totalRevenueFuture.get());

        System.out.println("Revenue Per City: "
                + revenuePerCityFuture.get());

        System.out.println("Top 3 Categories: "
                + topCategoriesFuture.get());

        executorService.shutdown();
    }

}
