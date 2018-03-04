package server.controller;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import server.model.*;
import java.io.*;
import java.net.*;

public class Connection implements Runnable {

    private static final Logger logger = Logger.getLogger(Connection.class);

    final private Socket socket;
    private PrintWriter writer;
    private boolean isWork = true;

    public Connection(Socket socket) {
        this.socket = socket;
        try {
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            logger.warn("connectionNewInstanse", e);
        }
    }

    public void run() {
        while (isWork) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = reader.readLine();
                System.out.println(message);
                if (message != null && !message.equals("")) {
                    String response = XmlConfiguration.getInstance().configuration(message);

                    String login = checkNewUser(message);
                    if(!login.equals("")){
                        Server.getInstance().setUser(login, this);
                    }
                    send(response);
                }
            } catch (IOException e) {
                logger.warn("readlineEx",e);
            }
        }
        try {
            socket.close();
        } catch (IOException e) {
            logger.warn("Close", e);
        }

    }

    public void send(String message) {
        writer.flush();
        writer.println(message);
        writer.flush();
        System.out.println(message);
    }

    public boolean checkConnection() {
            try {
                send("<test></test>");
                return true;
            } catch (Exception e) {
                isWork = false;
               return false;
            }
    }
    private String checkNewUser(String command){
        Document document = XmlConfiguration.newDocument(command);
        NodeList nodes = document.getElementsByTagName("command");
        Element element = (Element) nodes.item(0);
        String type = element.getAttribute("type");
        switch (type) {
            case "registration":
            case "login" :
                return element.getAttribute("login");
            case "addMessage":
            case "newChatID":
            case "addToChat":
                long id = Long.parseLong(element.getAttribute("chat_id"));
                Server.getInstance().sendToChat(id,command, this);
                return "";
            default :
                return "";
        }
    }
}

