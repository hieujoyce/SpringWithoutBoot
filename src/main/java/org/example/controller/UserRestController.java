package org.example.controller;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.example.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
public class UserRestController {
    public static HashMap<Integer, User> mapUser = new HashMap<Integer, User>();

    static {
        mapUser.put(1, new User(1, "Nam", "abc@gmail.com", "Ha Noi - Viet Nam"));
        mapUser.put(2, new User(2, "Darius", "darius@gmail.com", "New York - USA"));
        mapUser.put(3, new User(3, "Rooney", "rooney@gmail.com", "London - England"));
        mapUser.put(4, new User(4, "Kagawa", "kagawa@yahoo.com", "Tokyo - Japan"));
    }

    /* ---------------- GET ALL USER ------------------------ */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser() {
        log.info("Hieu Joyce check test");
        List<User> listUser = new ArrayList<>(mapUser.values());
        return new ResponseEntity<List<User>>(listUser, HttpStatus.OK);
    }

    @ToString()
    @XmlRootElement(name = "TestBodyXML")
    public static class TestBodyXML {

        @XmlElement(name = "username")
        public String username;
        @XmlElement(name = "password")
        public String password;

        public TestBodyXML(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public TestBodyXML() {
        }
    }

    @ToString()
    public static class TestBody {
        public String username;
        public String password;

        public TestBody(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public TestBody() {
        }
    }

    @RequestMapping(value = "/users_xml", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<User>> addUserXML(@RequestBody TestBodyXML body) {
        log.info("Body: " + body.username);

        List<User> listUser = new ArrayList<>(mapUser.values());
        return new ResponseEntity<List<User>>(listUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<User>> addUser(@RequestBody TestBody body) {
        log.info("Body: " + body.username);

        List<User> listUser = new ArrayList<>(mapUser.values());
        return new ResponseEntity<List<User>>(listUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ResponseEntity<String> test() {
        log.info("Check test: ");

        return new ResponseEntity<String>("Test Empty body", HttpStatus.OK);
    }
}
