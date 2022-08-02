package com.example.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static org.apache.kafka.streams.state.RocksDBConfigSetter.LOG;

@SpringBootApplication
@RestController
public class KafkaApplication {

	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	@GetMapping("/send")
	public void sendMessage(){
		kafkaTemplate.send("topic1","Message: " + new Date());
		return;
	}

	@GetMapping("/send/async")
	public void sendMessageAsync(){
		String message = "Message: " + new Date();
		ListenableFuture<SendResult<String, String>> future =
				kafkaTemplate.send("topic2",message );

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				LOG.info("Message [{}] delivered with offset {}",
						message,
						result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				LOG.warn("Unable to deliver message [{}]. {}",
						message,
						ex.getMessage());
			}
		});

		return;
	}

}
