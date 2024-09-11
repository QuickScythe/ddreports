package me.ddreports.server;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class Api {
    @GetMapping("/api/graph-data")
    public List<Integer> getGraphData() {
        // Example data, replace with your actual data source
        return Arrays.asList(10, 20, 15, 36, 27);
    }


}
