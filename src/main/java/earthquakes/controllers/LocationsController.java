package earthquakes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;
import java.util.HashMap;
import earthquakes.geojson.FeatureCollection;
import earthquakes.services.EarthquakeQueryService;
import earthquakes.searches.LocSearch;
import earthquakes.osm.Place;
import earthquakes.services.LocationQueryService;
import earthquakes.repositories.LocationRepository;
import earthquakes.entities.Location;

import java.util.List;

import com.nimbusds.oauth2.sdk.client.ClientReadRequest;

@Controller
public class LocationsController {
    private LocationRepository locationRepository;
    
    @Autowired
    public LocationsController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;   
    }
    
    @GetMapping("/locations/search")
    public String getLocationSearch(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken,
            LocSearch locSearch) {
                return "locations/search";
    }

    @GetMapping("/locations/results")
    public String getLocationResults(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken,
            LocSearch locSearch) {
        model.addAttribute("locSearch", locSearch);
        // TODO: Actually do the search here and add results to the model
	LocationQueryService b = new LocationQueryService();
	String json = b.getJSON(locSearch.getLocation());
        model.addAttribute("json", json);
        List<Place> place = Place.listFromJson(json);
        model.addAttribute("place", place);
	
        return "locations/results";
    }

    @GetMapping("/locations")    
    public String index(Model model, OAuth2AuthenticationToken token) {
        String uid = token.getPrincipal().getAttributes().get("id").toString();
        Iterable<Location> locations= locationRepository.findByUid(uid);
        model.addAttribute("locations", locations);
        return "locations/index";
    }
    
    
}
