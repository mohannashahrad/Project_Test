package model;

import java.util.HashMap;

public class Request {
    private int requestId;
    static private int lastRequestCode = 0;
    private RequestType typeOfRequest;
    private StateType stateOfRequest;
    private HashMap<String, String> information;

    public Request(String typeOfRequest, HashMap<String, String> information) {
        this.requestId = lastRequestCode + 1;
        lastRequestCode++;
        this.typeOfRequest = requestTypeFinder(typeOfRequest);
        this.information = information;
        this.stateOfRequest = StateType.PROCESSING;
    }

    public int getRequestId() {
        return requestId;
    }

    public int getLastRequestCode() {
        return lastRequestCode;
    }

    public RequestType getTypeOfRequest() {
        return typeOfRequest;
    }

    public StateType getStateOfRequest() {
        return stateOfRequest;
    }

    public HashMap<String, String> getInformation() {
        return information;
    }

    RequestType requestTypeFinder(String typeName) {
        switch (typeName) {
            case "register seller":
                return RequestType.REGISTER_SELLER;
            case "add product":
                return RequestType.ADD_PRODUCT;
            case "edit product":
                return RequestType.EDIT_PRODUCT;
            case "remove product":
                return RequestType.REMOVE_PRODUCT;
            case "add sale":
                return RequestType.ADD_SALE;
            case "edit sale":
                return RequestType.EDIT_SALE;
            case "add product to sale":
                return RequestType.ADD_PRODUCT_TO_SALE;
            case "remove product from sale":
                return RequestType.REMOVE_PRODUCT_FROM_SALE;
            case "add comment":
                return RequestType.ADD_COMMENT;
        }
        return null;
    }

    public void acceptRequest() {
        this.stateOfRequest = StateType.ACCEPTED;
    }

    public void declineRequest() {
        this.stateOfRequest = StateType.DECLINED;
    }
}
