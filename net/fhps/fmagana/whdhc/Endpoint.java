package net.fhps.fmagana.whdhc;

public enum Endpoint {
    ASSETS("Assets"),
    ASSET_STATUSES("AssetStatuses"),
    ASSET_TYPES("AssetTypes"),
    BULK_ACTIONS("TicketBulkActions"),
    CLIENTS("Clients"),
    COMPANIES("Companies"),
    DEPARTMENTS("Departments"),
    LOCATIONS("Locations"),
    MANUFACTURERS("Manufacturers"),
    MODELS("Models"),
    SETUP("Preferences"),
    PRIORITY_TYPES("PriorityTypes"),
    REQUEST_TYPES("RequestTypes"),
    ROOMS("Rooms"),
    STATUS_TYPES("StatusTypes"),
    TECHS("Techs"),
    TICKETS("Tickets"),
    TICKET_NOTES("TicketNotes");

    private String uri;

    private Endpoint(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
