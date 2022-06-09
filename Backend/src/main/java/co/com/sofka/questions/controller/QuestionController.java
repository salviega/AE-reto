package co.com.sofka.questions.controller;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecases.AddAnswerUseCase;
import co.com.sofka.questions.usecases.CreateUseCase;
import co.com.sofka.questions.usecases.DeleteUseCase;
import co.com.sofka.questions.usecases.GetUseCase;
import co.com.sofka.questions.usecases.ListUseCase;
import co.com.sofka.questions.usecases.OwnerListUseCase;
import co.com.sofka.questions.usecases.UpdateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RequestMapping
@RestControllerAdvice
public class QuestionController {

    private final AddAnswerUseCase addAnswerUseCase;
    private final CreateUseCase createUseCase;
    private final DeleteUseCase deleteUseCase;
    private final GetUseCase getUseCase;
    private final ListUseCase listUseCase;
    private final OwnerListUseCase ownerListUseCase;
    private final UpdateUseCase updateUseCase;

    public QuestionController(AddAnswerUseCase addAnswerUseCase, CreateUseCase createUseCase, DeleteUseCase deleteUseCase, GetUseCase getUseCase, ListUseCase listUseCase, OwnerListUseCase ownerListUseCase, UpdateUseCase updateUseCase) {
        this.addAnswerUseCase = addAnswerUseCase;
        this.createUseCase = createUseCase;
        this.deleteUseCase = deleteUseCase;
        this.getUseCase = getUseCase;
        this.listUseCase = listUseCase;
        this.ownerListUseCase = ownerListUseCase;
        this.updateUseCase = updateUseCase;
    }

    @GetMapping(path = "/getAll")
    @Operation(summary = "Get all the questions")
    @ApiResponse(responseCode = "200",
            description = "Found all the questions",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = QuestionDTO.class))})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Flux<QuestionDTO>> getAll() {
        return new ResponseEntity<>(listUseCase.get(), HttpStatus.OK);
    }

    @GetMapping(path = "/getOwnerAll/{userId}")
    @Operation(summary = "Get all user's questions by its id")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Flux<QuestionDTO>> getOwnerAll(@PathVariable("userId") String id) {
        return new ResponseEntity<>(ownerListUseCase.apply(id), HttpStatus.OK);
    }

    @GetMapping(path = "/getQuestion/{id}")
    @Operation(summary = "Get a questions by its id")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Mono<QuestionDTO>> getQuestionById(@PathVariable("id") String id) {
        return new ResponseEntity<>(getUseCase.apply(id), HttpStatus.OK);
    }

    @PostMapping(path = "/createQuestion")
    @Operation(summary = "Create a new question")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiResponse(responseCode = "201",
            description = "Create a question",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = QuestionDTO.class))})
    public ResponseEntity<Mono<Question>> create(@RequestBody QuestionDTO question) {

        return new ResponseEntity<>(createUseCase.apply(question), HttpStatus.CREATED);
    }

    @PostMapping(path = "/addAnswer")
    @Operation(summary = "Create a new answer")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Mono<QuestionDTO>> addAnswer(@RequestBody AnswerDTO answer) {
        return new ResponseEntity<>(addAnswerUseCase.apply(answer), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteQuestion/{id}")
    @Operation(summary = "Delete a question")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Mono<Void>> deleteQuestion(@PathVariable("id") String id) {
        return new ResponseEntity<>(deleteUseCase.apply(id), HttpStatus.NO_CONTENT);
    }

}