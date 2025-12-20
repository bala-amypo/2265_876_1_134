npackage com.example.demo.controller;

import com.example.demo.model.RecoveryCurveProfile;
import com.example.demo.service.RecoveryCurveService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recovery-curves")
@Tag(name = "Recovery Curves")
public class RecoveryCurveController {

    private final RecoveryCurveService service;

    public RecoveryCurveController(RecoveryCurveService service) {
        this.service = service;
    }

    @PostMapping
    public RecoveryCurveProfile create(@RequestBody RecoveryCurveProfile profile) {
        return service.createCurveEntry(profile);
    }

    @GetMapping("/surgery/{surgeryType}")
    public List<RecoveryCurveProfile> getBySurgery(
            @PathVariable String surgeryType
    ) {
        return service.getCurveForSurgery(surgeryType);
    }

    @GetMapping
    public List<RecoveryCurveProfile> getAll() {
        return service.getAllCurves();
    }
}
