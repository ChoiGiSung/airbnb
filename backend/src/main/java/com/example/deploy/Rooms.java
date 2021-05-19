package com.example.deploy;

public class Rooms {
    private Long id;
    private String name;
    private int pricePerData;
    private HomeDetail homeDetail;
    private String type;
    private boolean wish;
    private String thumbnailImage;
    private Coordinate coordinate;


    public Rooms(Long id, String name, int pricePerData, HomeDetail homeDetail, String type,
                 boolean wish, String thumbnailImage, Coordinate coordinate) {
        this.id = id;
        this.name = name;
        this.pricePerData = pricePerData;
        this.homeDetail = homeDetail;
        this.type = type;
        this.wish = wish;
        this.thumbnailImage = thumbnailImage;
        this.coordinate = coordinate;
    }

    public static class Coordinate{
        private double latitude;
        private double longitude;

        public Coordinate(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
    }

    public static class HomeDetail{
        private int maxGuest;
        private int bed;
        private int bathroom;

        public HomeDetail(int maxGuest, int bed, int bathroom) {
            this.maxGuest = maxGuest;
            this.bed = bed;
            this.bathroom = bathroom;
        }

        public int getMaxGuest() {
            return maxGuest;
        }

        public int getBed() {
            return bed;
        }

        public int getBathroom() {
            return bathroom;
        }


    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPricePerData() {
        return pricePerData;
    }

    public HomeDetail getHomeDetail() {
        return homeDetail;
    }

    public String getType() {
        return type;
    }

    public boolean isWish() {
        return wish;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
