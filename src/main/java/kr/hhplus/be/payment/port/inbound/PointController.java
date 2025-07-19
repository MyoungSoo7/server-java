package kr.hhplus.be.payment.port.inbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.points.dto.CustomerPointHistoryDto;
import kr.hhplus.be.payment.domain.service.PointService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/point")
@RequiredArgsConstructor
// 클라이언트가 도메인 기능에 접근할 수 있도록 정의
public class PointController {

    private static final Logger log = LoggerFactory.getLogger(PointController.class);
    private final PointService pointService;

    @GetMapping("{id}")
    public int point(
            @PathVariable long id
    ) {
        return pointService.selectUserPoint(id);
    }


    @GetMapping("{id}/histories")
    public CustomerPointHistoryDto history(
            @PathVariable long id
    ) {
        return pointService.selectUserPointHistory(id);
    }


    @PatchMapping("{id}/charge")
    public void charge(
            @PathVariable long id,
            @RequestBody int amount
    ) {
        pointService.charge(id, amount);
        log.info("charge point : {}", amount);
    }

    @PatchMapping("{id}/use")
    public void use(
            @PathVariable long id,
            @RequestBody int amount
    ) {
        pointService.use(id, amount);
        log.info("use point : {}", amount);
    }


}
