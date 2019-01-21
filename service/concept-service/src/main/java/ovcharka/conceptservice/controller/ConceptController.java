package ovcharka.conceptservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovcharka.common.controller.AbstractController;
import ovcharka.common.payload.AbstractResponse;
import ovcharka.common.payload.BooleanResponse;
import ovcharka.conceptservice.domain.Concept;
import ovcharka.conceptservice.payload.request.ConceptUpdateRequest;
import ovcharka.conceptservice.payload.request.ConceptsUpdateRequest;
import ovcharka.conceptservice.payload.response.ConceptListResponse;
import ovcharka.conceptservice.payload.response.ConceptResponse;
import ovcharka.conceptservice.payload.response.WordListResponse;
import ovcharka.conceptservice.service.ConceptService;

@RestController
public class ConceptController extends AbstractController {

    private final ConceptService conceptService;

    @Autowired
    public ConceptController(ConceptService conceptService) {
        this.conceptService = conceptService;
    }

    @GetMapping
    ResponseEntity<AbstractResponse> getAllConcepts() {
        return getResponse(
                () -> new ConceptListResponse(
                        conceptService.findAll()
                )
        );
    }

    @GetMapping(params = "word")
    ResponseEntity<AbstractResponse> getConcept(@RequestParam("word") String word) {
        return getResponse(
                () -> new ConceptResponse(
                        conceptService.findByWord(word)
                )
        );
    }

    @GetMapping("/words")
    ResponseEntity<AbstractResponse> getAllWords() {
        return getResponse(
                () -> new WordListResponse(
                        conceptService.findAllWords()
                )
        );
    }

    @GetMapping("/words/related")
    ResponseEntity<AbstractResponse> getRelated(@RequestParam("word") String word) {
        return getResponse(
                () -> new WordListResponse(
                        conceptService.findRelated(word)
                )
        );
    }

    @PostMapping("/words")
    ResponseEntity<AbstractResponse> updateWords(@RequestBody ConceptsUpdateRequest request) {
        return getResponse(
                () -> {
                    conceptService.updateConcepts(request.getWords());
                    return new BooleanResponse(true);
                }
        );
    }

    @PostMapping("/word")
    ResponseEntity<AbstractResponse> updateWord(@RequestBody ConceptUpdateRequest request) {
        return getResponse(
                () -> {
                    conceptService.updateConcept(
                            Concept.of(
                                    request.getWord(),
                                    request.getDefinition(),
                                    request.getScore()
                            )
                    );
                    return new BooleanResponse(true);
                }
        );
    }
}
