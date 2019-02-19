package hb.zipkin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ApplicationForZipkinServer {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationForZipkinServer.class, args);
    }
}