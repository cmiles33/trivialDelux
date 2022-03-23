/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerPack;

import modelPack.Track;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 * This is the board track class.
 * <p>
 * The function of this class is to hold the tracks for each section of the board
 * in practice there will be six sections, each section having 12 tracks, each
 * instance of this class keeps track of its own 12 tracks.
 * @author calebmiles
 */
public class BoardTrack {
    /**
     * This this function sets the tracklist
     * @param listTracks sets trackslist
     */
    public void setTrackList( List<Track> listTracks )
    {
        this.boardLocation = listTracks;
    }
    /**
     * This function returns the tracklist
     * @return the object tracklist.
     */
    public List<Track> getTrackList()
    {
        return this.boardLocation;
    }
    /**
     * Adds tracks to the objects tracklist
     * @param newTrack takes in a track
     */
    public void addTrack(Track newTrack)
    {
        this.boardLocation.add(newTrack);
    }
    // Contains the list of tracks.
    private List<Track> boardLocation = new ArrayList<Track>();
    
}
