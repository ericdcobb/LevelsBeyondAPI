package com.palancarmedia;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("api")
public class NoteApp {
	
	static class Note {
		public int id;
		public String body;
		
		public Note(int i, String txt) {
			id = i;
			body = txt;
		}
	}
	
	static class Input {
		public String tag;
		public String body;
	}
	
	private static Map<Integer, String> notes = null;
	private static GsonBuilder builder = null;
	private static Gson gson = null;
	
	static {
		builder = new GsonBuilder();
		gson = builder.create();
		notes = new HashMap<Integer, String>();
	}
	
        
    @Path("notes/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getNote(@PathParam("id") final int id) {
        
    	String result = "";
    	String err="";
    	
    	if (notes.isEmpty()) {
    		err = "{\"Error\":\"No notes found\"}";
    		return Response.status(500).entity(err).build();
    	}
    	
    	if (notes.containsKey(id)) {
    		Note n = new Note(id, notes.get(id));
    		Type nodeType = new TypeToken<Note>(){}.getType();
    		result = gson.toJson(n, nodeType);
    		return Response.ok(result, MediaType.APPLICATION_JSON).build();
    	}
    	else {
    		
    		String[] msg = {"Error", "Note id" + id + " not found"}; 
    		result = gson.toJson(msg);
    		return Response.ok(result, MediaType.APPLICATION_JSON).build();
    	}
    	
    }
    
    @Path("notes")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNote(final String msg) {
    	
    	String result="";
    	int noteId=0;
    
    	if (notes.isEmpty())
    		noteId = 1;
    	else
    		noteId = Collections.max(notes.keySet()) + 1;
    	
    	Type ntype = new TypeToken<Input>(){}.getType();
		Input nn = gson.fromJson(msg, ntype);
				
    	notes.put(noteId, nn.body);
		
		Note n = new Note(noteId, notes.get(noteId));
		Type nodeType = new TypeToken<Note>(){}.getType();
		
		result = gson.toJson(n, nodeType);
		return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }
    
    @Path("notes")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response queryNotes(@QueryParam("query") final String qry) {
        
    	String result = "";
    	String err="";
    	
    	if (notes.isEmpty()) {
    		err = "{\"Error\":\"No notes found\"}";
    		return Response.status(500).entity(err).build();
    	}
    	
    	if (qry != null) {
    		List<Note> nts = new ArrayList<Note>();
        	for (int i:notes.keySet()) {
        		if (notes.get(i).contains(qry)) {
        			nts.add(new Note(i, notes.get(i)));
        		}
        	}

        	if (nts.size() > 0) {
    	    	Type collectionType = new TypeToken<List<Note>>(){}.getType();		
    			result = gson.toJson(nts, collectionType);
    	    	
    	    	return Response.ok(result, MediaType.APPLICATION_JSON).build();
        	}
        	else {
        		err = "{\"Error\":\"No notes found for query\"}";
        		return Response.status(500).entity(err).build();
        	} 
    	}
    	else {
	    	List<Note> nt = new ArrayList<Note>();
			for (int x:notes.keySet()) {
				nt.add(new Note(x, notes.get(x)));
			}
		
			Type collectionType = new TypeToken<List<Note>>(){}.getType();		
			result = gson.toJson(nt, collectionType);
	    	
	    	return Response.ok(result, MediaType.APPLICATION_JSON).build();
    	}
	    
    }
    
   
}
