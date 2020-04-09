package ProducerConsumer;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

public class PC_WAIT_NOTIFY {

}
class MessageQueue {

    //双向队列
    private LinkedList<Message> list = new LinkedList<>();
    private int capacity;

    public MessageQueue(int capacity){
        this.capacity = capacity;
    }
    //获取消息
    public Message take() {
        //检查队列是否为空
        synchronized (list){
            while (list.isEmpty()){
                try {
                    System.out.println("队列为空，消费者等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //从队列头部获取消息
            Message m = list.removeFirst();
            System.out.println("已消费消息"+m);
            list.notify();
            return m;
        }
    }

    public void put(Message message){
        synchronized (list) {
            while (list.size() == capacity){
                try {
                    System.out.println("队列已满，生产者等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.addLast(message);
            System.out.println("已生产消息"+message);
            list.notifyAll();
        }
    }
}

//不能被继承
final class Message<T> {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    private int id;
    private T value;

    public Message(int id,T value) {
        this.id = id;
        this.value = value;
    }

    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(2);

        for (int i = 0;i < 3;i++) {
            int id = i;
            new Thread(()->{
                messageQueue.put(new Message(id,"值"+id));
            },"生产者"+i).start();
        }

        new Thread(()->{
            while (true) {
                try {
                    sleep(1);
                    Message m = messageQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"消费者").start();
    }
}
