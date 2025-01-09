package org.example

import java.util.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

data class Order(val id: Int, val shoeType: String, val quantity: Int)

class ShoeWarehouse : Object() {
    private val logger: Logger = LoggerFactory.getLogger(ShoeWarehouse::class.java)

    companion object {
        val shoeTypes: List<String> = listOf("Кроссовки", "Ботинки", "Туфли")
    }

    private val orders: LinkedList<Order> = LinkedList()
    private val maxOrders = 10

    @Synchronized
    fun receiveOrder(order: Order) {
        while (orders.size >= maxOrders)
            wait()
        orders.add(order)
        logger.info("Получен заказ: $order")
        notifyAll()
    }

    @Synchronized
    fun fulfillOrder(): Order {
        while (orders.isEmpty())
            wait()
        val order = orders.poll()
        logger.info("Исполнен заказ: $order")
        notifyAll()
        return order
    }
}

class Producer(private val warehouse: ShoeWarehouse, private val orderCount: Int) : Runnable {
    override fun run() {
        for (i in 1..orderCount) {
            val shoeType = ShoeWarehouse.shoeTypes.random()
            val order = Order(i, shoeType, (1..10).random())
            warehouse.receiveOrder(order)
            Thread.sleep(500)
        }
    }
}

class Consumer(private val warehouse: ShoeWarehouse, private val ordersToFulfill: Int) : Runnable {
    override fun run() {
        repeat(ordersToFulfill) {
            warehouse.fulfillOrder()
            Thread.sleep(1500)
        }
    }
}

fun main() {
    // 1
    val evenThread = object : Thread() {
        override fun run() {
            for (i in 0 until 10 step 2)
                println("Четное число: $i")
        }
    }

    val oddThread = Thread {
        for (i in 1 until 10 step 2)
            println("Нечетное число: $i")
    }

    evenThread.start()
    oddThread.start()

    // 2
    val warehouse = ShoeWarehouse()
    val totalOrders = 20

    val producer = Producer(warehouse, totalOrders)
    val producerThread = Thread(producer)
    producerThread.start()

    val consumerCount = totalOrders / 5
    repeat(consumerCount) {
        val consumer = Consumer(warehouse, 5)
        val consumerThread = Thread(consumer)
        consumerThread.start()
    }
    producerThread.join()
}