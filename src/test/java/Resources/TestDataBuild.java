package Resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name,String language,String address){

        //body creation using POJO class and serialization
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setName(name);
        addPlace.setPhone_number("123456789");

        List<String> myListTypes = new ArrayList<String>();
        myListTypes.add("shoe park");
        myListTypes.add("shop");
        addPlace.setTypes(myListTypes);

        Location loc = new Location();
        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        addPlace.setLocation(loc);

        return addPlace;
    }

    public String deletePlacePayload(String placeid){
        return "{\n" +
                "\"place_id\":\""+placeid+"\"\n" +
                "}";
    }

}
