package br.com.bank.bank.adapter.`in`.web
import br.com.bank.bank.adapter.`in`.web.dto.ActivityDto
import br.com.bank.bank.adapter.`in`.web.mapper.ActivityWebMapper
import br.com.bank.bank.application.`in`.IQueryActivityUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/activities")
class ActivityController
@Autowired constructor(
        val queryActivityService: IQueryActivityUseCase,
        val activityWebMapper: ActivityWebMapper) {

    @GetMapping("/{accountId}")
    fun queryActivities(@PathVariable accountId: Long): ResponseEntity<List<ActivityDto>> {
        val activitiesDto: List<ActivityDto> = queryActivityService.findActivitiesByAccount(accountId).map { activityWebMapper.toDto(it) }.toList()

        return ResponseEntity.ok(activitiesDto);
    }
}