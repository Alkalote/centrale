package fr.hb.jg.centrale.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.jg.centrale.json_view.JsonViews;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({JsonViews.BrandList.class,JsonViews.BrandShow.class})
    private Long id;

    @NotBlank
    @JsonView({JsonViews.ListingShow.class,JsonViews.ModelList.class,JsonViews.BrandList.class,JsonViews.BrandShow.class})
    private String name;

    @OneToMany(mappedBy = "brand")
    @JsonView({JsonViews.BrandShow.class})
    private List<Model> models = new ArrayList<>();

    @JsonView(JsonViews.BrandList.class)
    public int modelCount(){
        return models.size();
    }
}