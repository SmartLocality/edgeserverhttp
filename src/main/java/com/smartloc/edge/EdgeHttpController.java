package com.smartloc.edge;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by shivanggupta on 18/11/16.
 */
@RestController
public class EdgeHttpController {

    @RequestMapping(method = RequestMethod.GET, name = "/test")
    public String testGet() {
        return "hello";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/send")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendPacket(@RequestBody ForwardRequest forwardRequest) throws Exception {
        String payLoad = forwardRequest.getDeviceId() + "," +
                forwardRequest.getValue();
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("192.168.0.100");
        byte[] sendData = new byte[1024];
        sendData = payLoad.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9090);
        clientSocket.send(sendPacket);
        clientSocket.close();
        System.out.println(payLoad);
    }

    // Gets the IP of the BBB to which the device is attached
    private String getIpToForwardReq() {
        return "";
    }
}
