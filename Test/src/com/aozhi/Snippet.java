package com.aozhi;

import java.util.Properties;

public class Snippet {
	public static void main(String[] args) throws Exception {
	    Properties props = new Properties();
	    props.put("broker.list", "0:127.0.0.1:9092");
	    props.put("serializer.class", StringEncoder.class.getName());
	    //
	    ProducerConfig config = new ProducerConfig(props);
	    Producer<String, String> producer = new Producer<String, String>(config);
	    //
	    StringProducerData data = new StringProducerData("demo");
	    for(int i=0;i<1000;i++) {
	        data.add("Hello world #"+i);
	    }
	    //
	    try {
	        long start = System.currentTimeMillis();
	        for (int i = 0; i < 100; i++) {
	            producer.send(data);
	        }
	        long cost = System.currentTimeMillis() - start;
	        System.out.println("send 100000 message cost: "+cost+" ms");
	    } finally {
	        producer.close();
	    }
	}
}

