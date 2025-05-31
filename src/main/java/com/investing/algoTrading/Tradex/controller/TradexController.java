package com.investing.algoTrading.Tradex.controller;

import com.investing.algoTrading.Tradex.model.Position;
import com.investing.algoTrading.Tradex.service.KiteConnectService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TradexController {

    private final KiteConnectService kiteConnectService;

    public TradexController(KiteConnectService kiteConnectService){
        this.kiteConnectService = kiteConnectService;
    }


    @GetMapping("/login-url")
    public ResponseEntity<String> getLoginUrl() {
        String loginURL = kiteConnectService.getLoginURL();
        return ResponseEntity.ok(loginURL);
    }

    @PostMapping("/session")
    public ResponseEntity<String> createSession(@RequestBody Map<String, String> body) {
        String requestToken = body.get("requestToken");

        if (requestToken == null || requestToken.isEmpty()) {
            return ResponseEntity.badRequest().body("Missing request token");
        }

        boolean isSessionCreated = kiteConnectService.createKiteConnectSession(requestToken);
        if(isSessionCreated) {
            return ResponseEntity.ok("Session created successfully");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Session creation failure");
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> brokerLogout(){
        kiteConnectService.brokerLogout();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/positions")
    public ResponseEntity<List<Position>> getPositions(){
        List<Position> positions = kiteConnectService.getPositions();
        return ResponseEntity.ok(positions);
    }

}
