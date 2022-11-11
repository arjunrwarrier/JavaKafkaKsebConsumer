import netscape.javascript.JSObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Properties;

public class KsbeConsumer {
    public static void main(String[] args) {
        KafkaConsumer consumer;
        String topic = "kseb";
        String broker = "localhost:9092";
        Properties props = new Properties();
        props.put("bootstrap.servers",broker);
        props.put("group.id", "test-group");
        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer(props);
        consumer.subscribe(Arrays.asList(topic));

        while(true){
            ConsumerRecords<String,String> records = consumer.poll(100);
            for(ConsumerRecord<String,String> record : records){
                System.out.println(record.value());
                System.out.println(String.valueOf(record.value()));
                JSONObject obj = new JSONObject(record.value());
                String userId = String.valueOf(obj.getInt("userid"));
                String unit = String.valueOf(obj.getInt("unit"));
                System.out.println(userId);
                System.out.println(unit);





            }

        }



    }
}
