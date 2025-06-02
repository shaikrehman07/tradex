package com.investing.algoTrading.Tradex.controller;

import com.investing.algoTrading.Tradex.model.KiteSession;
import com.investing.algoTrading.Tradex.model.Position;
import com.investing.algoTrading.Tradex.service.BrokerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TradexController {

    private final BrokerService kiteConnectService;

    public TradexController(BrokerService kiteConnectService){
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
        kiteConnectService.brokerLogout(authHeader);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/positions")
    public ResponseEntity<List<Position>> getPositions(@RequestHeader("Authorization") String authHeader){
        List<Position> positions = kiteConnectService.getPositions(authHeader);
        return ResponseEntity.ok(positions);
    }

}
