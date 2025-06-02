package com.investing.algoTrading.Tradex.controller;

import com.investing.algoTrading.Tradex.model.KiteSession;
import com.investing.algoTrading.Tradex.model.Position;
import com.investing.algoTrading.Tradex.service.KiteConnectService;

import org.json.JSONObject;
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


    @PostMapping("/session")
    public ResponseEntity<KiteSession> createSession(@RequestHeader("Authorization") String requestToken) {

        if (requestToken == null || requestToken.isEmpty()) {
            throw new RuntimeException("Request token not present.");
        }

        KiteSession session = kiteConnectService.createKiteConnectSession(requestToken);

        if(session.getLoginStatus()){
            return ResponseEntity.ok(session);
        }

        return ResponseEntity.ok(new KiteSession());

    }

    @GetMapping("/logout")
    public ResponseEntity<Void> brokerLogout(@RequestHeader("Authorization") String authHeader){
        kiteConnectService.setToken(authHeader);
        kiteConnectService.brokerLogout();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/positions")
    public ResponseEntity<List<Position>> getPositions(@RequestHeader("Authorization") String authHeader){
        kiteConnectService.setToken(authHeader);
        List<Position> positions = kiteConnectService.getPositions();
        return ResponseEntity.ok(positions);
    }

}
