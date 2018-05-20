package com.example.nikit.eventsapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by harsimar on 20/05/18.
 */

public class Event {
    @SerializedName("identifier")
    private String identifier;
    @SerializedName("name")
    private String name;
    private Double latitude;
    private Double longitude;
    private String locationName;
    private String startsAt;
    private String endsAt;
    private String timezone;
    private String description;
    private String logoUrl;
    private String organizerName;
    private String organizerDescription;
    private String ticketUrl;
    private String privacy;
    private String type;
    private String topic;
    private String subTopic;
    private String codeOfConduct;
    private String email;
    private String schedulePublishedOn;
    private String searchableLocationName;
    private String state;
    private boolean isSessionsSpeakersEnabled;
    private String thumbnailImageUrl;
    private String originalImageUrl;
    private String largeImageUrl;
    private String iconImageUrl;
    private String createdAt;
    private String deletedAt;

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getStartsAt() {
        return startsAt;
    }

    public String getEndsAt() {
        return endsAt;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getDescription() {
        return description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public String getOrganizerDescription() {
        return organizerDescription;
    }

    public String getTicketUrl() {
        return ticketUrl;
    }

    public String getPrivacy() {
        return privacy;
    }

    public String getType() {
        return type;
    }

    public String getTopic() {
        return topic;
    }

    public String getSubTopic() {
        return subTopic;
    }

    public String getCodeOfConduct() {
        return codeOfConduct;
    }

    public String getEmail() {
        return email;
    }

    public String getSchedulePublishedOn() {
        return schedulePublishedOn;
    }

    public String getSearchableLocationName() {
        return searchableLocationName;
    }

    public String getState() {
        return state;
    }

    public boolean isSessionsSpeakersEnabled() {
        return isSessionsSpeakersEnabled;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public String getOriginalImageUrl() {
        return originalImageUrl;
    }

    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    public String getIconImageUrl() {
        return iconImageUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }
}