//package br.com.go5.brokerapplication.config;
//
//import br.com.go5.brokerapplication.repository.StockRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Random;
//import java.util.concurrent.TimeUnit;
//
//@Component
//public class ScheduledTasks  {
//
//    @Autowired
//    StockRepository stockRepository;
//
//    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
//    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//    private final Double rangeMin = 0.1;
//    private final Double rangeMax = 0.4;
//
//    @Scheduled(fixedRate = 5000)
//    public void scheduleTaskWithFixedRate() {
//        Double value = getRandomUpdateValue();
//        stockRepository.updateStocksPrices(value);
//        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) + " value: " + value);
//    }
//
//    private Double getRandomUpdateValue() {
//        Random random = new Random();
//        int randomSign = random.nextBoolean() ? 1 : -1;
//        return (rangeMin + (rangeMax - rangeMin) * random.nextDouble()) * randomSign;
//    }
//
//}