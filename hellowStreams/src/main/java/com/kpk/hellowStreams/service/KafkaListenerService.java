package com.kpk.hellowStreams.service;

import com.kpk.hellowStreams.binding.KafkaListenerBinding;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Log4j2
@EnableBinding(KafkaListenerBinding.class)
public class KafkaListenerService {

    @StreamListener("input-channel-1")
    public void process(KStream<String, byte[]> input){

        input.foreach((k, v)-> {

            if(v.length==0){
                log.error("Attachment something went wrong ............");
            }else {
                log.info("Your file is saved Successfully. file.length :"+v.length);

                File file = new File("filesave/"+k);
                try {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    outputStream.write(v);
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
       /* input.foreach((k, v)->log.info(String.format("Key: [%s], Value: [%s]",k,v)));*/

    }
}
