package handlers;

import statistic.StatisticService;

import java.util.concurrent.ExecutorService;

public interface EventHandler <T>{
    void process (T t, ExecutorService executor);
}
