package com.fofok.figuresapi.interfaces;

import com.fofok.figuresapi.response.FigureBody;
import com.fofok.figuresapi.response.FigureType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
@RequestMapping
@RestController
public interface Figure {

    @GetMapping(
            value = "/figures",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Get all figures available",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "An internal error occurred",
                            content = @Content,
                            responseCode = "500"
                    )
            }
    )
    ResponseEntity<List<FigureBody>> getAllFigures(
            @RequestParam(value = "type", defaultValue = "") FigureType type
    );

    @GetMapping(
            value = "/figure/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<String> getFigureById(@PathVariable("id") String id);

    @PutMapping(
            value = "/figure/{id}",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    ResponseEntity<String> updateFigureById(@PathVariable("id") String id);

    @PostMapping(
            value = "/figure/{id}",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    ResponseEntity<String> addFigureById(@PathVariable("id") String id);

    @DeleteMapping(
            value = "/figure/{id}",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    ResponseEntity<String> deleteFigureById(@PathVariable("id") String id);

}
