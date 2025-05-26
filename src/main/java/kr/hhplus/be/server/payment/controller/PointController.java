package kr.hhplus.be.server.payment.controller;

import kr.hhplus.be.server.payment.entity.PointHistory;
import kr.hhplus.be.server.payment.entity.UserPoint;
import kr.hhplus.be.server.payment.service.PointService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {

    private static final Logger log = LoggerFactory.getLogger(PointController.class);
    private final PointService pointService;

    @GetMapping("{id}")
    public UserPoint point(
            @PathVariable long id
    ) {
        return pointService.selectUserPoint(id);
    }


    @GetMapping("{id}/histories")
    public List<PointHistory> history(
            @PathVariable long id
    ) {
        return pointService.selectUserPointHistory(id);
    }


    @PatchMapping("{id}/charge")
    public UserPoint charge(
            @PathVariable long id,
            @RequestBody long amount
    ) {
        return pointService.chargeUserPoint(id, amount);
    }

    @PatchMapping("{id}/use")
    public UserPoint use(
            @PathVariable long id,
            @RequestBody long amount
    ) {
        return pointService.useUserPoint(id, amount);
    }


}
