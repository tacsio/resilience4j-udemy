package io.tacsio.publication.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Publication {

    private String id;
    private String title;
    private String imageUrl;
    private String text;
    private List<Comment> comments;
    private double random = Random.from(RandomGenerator.getDefault()).nextGaussian();
}
