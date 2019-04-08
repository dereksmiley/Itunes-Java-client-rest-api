/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author 7s5z9
 */
@Path("itunes")
public class ItunesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ItunesResource
     */
    public ItunesResource() {
    }

    /**
     * Retrieves representation of an instance of model.ItunesResource
     * @return an instance of java.lang.String
     */
    
    
    private HttpURLConnection connection(URL url) throws ProtocolException, IOException{
    
    HttpURLConnection conn; 
    conn = (HttpURLConnection) url.openConnection();   
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accecpt", "application/vnd.github.v3+json");
           
         
    
    return conn;
    }
  
    
    @GET
    @Path("artist/name/{name}/{entity}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getArtistByName(@PathParam("name") String name,
                                    @PathParam("entity") String entity) {
        
       String urlTraget  =  "https://itunes.apple.com/search?term=" + name + "&entity=" + entity;  
        StringBuilder stringBuilder = new StringBuilder();
        
        URL url;
        try {
            url = new URL(urlTraget);
//            HttpURLConnection conn = connection(url);
//            conn.connect();

            HttpURLConnection conn; 
          
            conn = (HttpURLConnection) url.openConnection();
  
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accecpt", "application/vnd.github.v3+json");
            
            InputStream inputStream = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream);
           
            
            BufferedReader bufferedReader = new BufferedReader(isr);
            for (String line ; (line = bufferedReader.readLine()) != null; ) {
                stringBuilder.append(line);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(ItunesResource.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
               Logger.getLogger(ItunesResource.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return stringBuilder.toString(); 
      
    }
    
    
    
    @GET
    @Path("artist/{id}/{entity}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getArtistById(@PathParam("id") String id,
                                     @PathParam("entity") String entity) {
    
        String urlTraget = "https://itunes.apple.com/lookup?id=" + id + "&entity=" + entity;
        StringBuilder stringBuilder = new StringBuilder();
        
        try{
            URL url = new URL(urlTraget);
//            HttpURLConnection conn = connection(url);
//            conn.connect();

    
    HttpURLConnection conn; 
    conn = (HttpURLConnection) url.openConnection();   
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accecpt", "application/vnd.github.v3+json");
            
            InputStream inputStream = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream);
           
            
            BufferedReader bufferedReader = new BufferedReader(isr);
            for (String line ; (line = bufferedReader.readLine()) != null; ) {
                stringBuilder.append(line);
            }
   
        }catch(IOException e){
            System.out.println(e.toString());
            
        }
              
        return stringBuilder.toString();
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of ItunesResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
