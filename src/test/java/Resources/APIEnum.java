package Resources;

public enum APIEnum {

    addPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");

    private String resource ;
    APIEnum(String resource){
        this.resource=resource;
    }

    public String getResource(){
        return resource;
    }
}
