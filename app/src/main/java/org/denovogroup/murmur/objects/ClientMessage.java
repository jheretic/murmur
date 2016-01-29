// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: /Users/barathraghavan/code/murmur/murmur/buck-out/gen/proto-repo/compile_protobufs__srcs/ClientMessage.proto
package org.denovogroup.murmur.objects;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okio.ByteString;

/**
 * Data sent by the "client" in a PSI exchange.
 */
public final class ClientMessage extends Message {

  public static final List<JSONObject> DEFAULT_MESSAGES = Collections.emptyList();
  public static final List<ByteString> DEFAULT_BLINDEDFRIENDS = Collections.emptyList();
    private static final String MESSAGES = "messages";
    private static final String FRIENDS = "friends";

  /**
   * The client's messages to propagate.
   */
  public List<JSONObject> messages;

  /**
   * The client's friends, blinded.
   */
  public List<ByteString> blindedFriends;

  public ClientMessage(ArrayList<JSONObject> messages, ArrayList<ByteString> blindedFriends) {
    this.messages = (messages != null) ?(List<JSONObject>)messages.clone() : DEFAULT_MESSAGES;
    this.blindedFriends = (blindedFriends != null) ? (List<ByteString>) blindedFriends.clone() :DEFAULT_BLINDEDFRIENDS;
  }

    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        JSONArray messagesArray = new JSONArray();
        JSONArray friendsArray = new JSONArray();

        for(JSONObject message : messages){
            messagesArray.put(message);
        }
        for(ByteString friend : blindedFriends){
            friendsArray.put(friend.base64());
        }
        try {
            json.put(MESSAGES,messagesArray);
            json.put(FRIENDS,friendsArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static ClientMessage fromJSON(JSONObject json){
        try {
            JSONArray messagesArray = json.getJSONArray(MESSAGES);
            JSONArray friendsArray = json.getJSONArray(FRIENDS);

            List<JSONObject> messages = new ArrayList<>();
            List<ByteString> friends = new ArrayList<>();

            for(int i=0; i<messagesArray.length(); i++){
                messages.add((JSONObject) messagesArray.get(i));
            }
            for(int i=0; i<friendsArray.length(); i++){
                friends.add(ByteString.decodeBase64((String) friendsArray.get(i)));
            }
            return new ClientMessage((ArrayList<JSONObject>)messages,(ArrayList<ByteString>)friends);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
