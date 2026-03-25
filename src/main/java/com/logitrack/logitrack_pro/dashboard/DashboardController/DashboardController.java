package com.logitrack.logitrack_pro.dashboard.DashboardController;

import com.logitrack.logitrack_pro.dashboard.DashboardService.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public Map<String, Object> dashboard(@RequestParam(required = false) Long veiculoId){
        return dashboardService.obterDashboard(veiculoId);
    }
}
