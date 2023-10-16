package org.example;

import java.io.IOException;

public class App
{
    public static void main( String[] args )
    {
        HttpStatusChecker checker = new HttpStatusChecker();

        try {
            String imageUrl1 = checker.getStatusImage(200);
            System.out.println("Status 200 Image URL: " + imageUrl1);

            String imageUrl2 = checker.getStatusImage(404);
            System.out.println("Status 404 Image URL: " + imageUrl2);

            // This will throw an exception since there's no image for code 10000
            String imageUrl3 = checker.getStatusImage(10000);
            System.out.println("Status 10000 Image URL: " + imageUrl3);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }


        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();

        try {
            downloader.downloadStatusImage(200);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            downloader.downloadStatusImage(10000);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

