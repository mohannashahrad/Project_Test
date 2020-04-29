package model;

public class Request {
    private int requestId = 0;
    private RequestType typeOfRequest;
    private StateType stateOfRequest;

    public Request(String typeOfRequest) {
        this.requestId = requestId + 1;
        this.typeOfRequest = requestTypeFinder(typeOfRequest);
        this.stateOfRequest = StateType.PROCESSING;
    }

    public int getRequestId() {
        return requestId;
    }

    public RequestType getTypeOfRequest() {
        return typeOfRequest;
    }

    public StateType getStateOfRequest() {
        return stateOfRequest;
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
