package fr.hb.jg.centrale.json_view;

import lombok.Data;
import org.springframework.ui.Model;

@Data
public class JsonViews {

    public interface UserMinimalView{}

    public interface UserShowView{}


    public interface ListingList{}
    public interface ListingShow extends ListingList {}

    public interface ModelList{}
    public interface ModelShow extends ModelList{}

    public interface BrandList{}
    public interface BrandShow{}


    public interface AddressShow{}

    public interface FuelList{}
    public interface FuelShow extends FuelList{}
}
