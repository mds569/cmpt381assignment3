package com.example.assignment3_mds569;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

public class PublishSubscribe {

    // It was requested that we use a map for this, so the HashMap seemed most appropriate.
    // I was going to just create an ArrayList for each channel, but that doesn't allow the
    // flexibility of creating new channels.
    private HashMap<String, ArrayList<Subscriber>> subs;

    // Instantiate the HashMap
    public PublishSubscribe(){
        subs = new HashMap<String, ArrayList<Subscriber>>();
    }

    // Add a subscriber to a channel
    public void addSubscriber(String chnl, Subscriber subscriber){
        // Call createChannel()
        createChannel(chnl);
        // If the channel DOES NOT contain that Subscriber already
        if (!subs.get(chnl).contains(subscriber)){
            // Add the subscriber to that channel
            subs.get(chnl).add(subscriber);
        }
    }

    // Remove a Subscriber from a channel
    public void removeSubscriber(String chnl, Subscriber subscriber){
        // If the channel exists
        if (subs.containsKey(chnl)){
            // If that channel is not empty
            if (!subs.get(chnl).isEmpty()){
                // Remove the subscriber from that channel
                subs.get(chnl).remove(subscriber);
            }
        }
    }

    // Create a new channel
    public void createChannel(String chnl) {
        // If the channel does not already exist
        if (!subs.containsKey(chnl)){
            // Create that channel and assign it an empty list of Subscribers
            subs.put(chnl, new ArrayList<Subscriber>());
        }
    }

    // Notify any Subscribers in the provided channel
    public void publish(String chnl) {
        // If the channel exists
        if (subs.containsKey(chnl)) {
            // If the channel is not empty
            if (!subs.get(chnl).isEmpty()) {
                // Create a separate list of those Subscribers
                ArrayList<Subscriber> toUpdate = subs.get(chnl);
                // And call each of their update methods with the channel (required by Subscriber interface)
                for (Subscriber subscriber : toUpdate) {
                    subscriber.getNotification(chnl);
                }
            }
        }
    }
}
